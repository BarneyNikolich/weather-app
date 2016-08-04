package controllers

import javax.inject.Inject

import models.WeatherRequest
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class WSExampleController @Inject()(metOfficeService: MetOfficeService, val messagesApi: MessagesApi) extends Controller with I18nSupport{

  def index = Action {
    Ok(views.html.index(WeatherRequest.weatherForm))
  }

  def submitWeatherRequest = Action { implicit request =>
    WeatherRequest.weatherForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.index(formWithErrors))
      },
      successForm => {
        Redirect(routes.WSExampleController.getWeatherData(successForm.town))
      }
    )
  }

  def getWeatherData(town: String) = Action.async{ implicit request =>
   metOfficeService.getLocations flatMap { response =>
      response match {
      case AllLocationsSuccessResponse(locationsList) =>

        if(locationsList.Locations.townExists(town)) {
          val townId = locationsList.Locations.getTown(town).map(_.id).get

          metOfficeService.getFiveDayForecast(townId) map { report =>
            report match {
              case FiveDayForecastSuccessResponse(response) =>
                val responsePrint = Json.prettyPrint(Json.toJson(response))
                Ok(responsePrint)
              case _ => BadRequest(request.headers.toString())
            }
          }
        } else Future.successful(BadRequest("Town doesn't exist!"))
      case _ => Future.successful(BadRequest("Locations call failed" ))
      }
    }
  }


def getAllLocations() = Action.async {
    {
      metOfficeService.getLocations map {
        response =>
          response match {
            case AllLocationsSuccessResponse(locationsList) =>
                Ok(Json.toJson(locationsList))
            case ExampleTimeOut(t) => InternalServerError
            case _ => BadRequest("TIMEOUT")
          }
      }
    }
  }


  def getFiveDayForecast(id: String) = Action.async {
    {
      metOfficeService.getFiveDayForecast(id) map {
        fiveDayForecast =>
          fiveDayForecast match {
            case FiveDayForecastSuccessResponse(forecast) =>
              Ok(forecast.toString)
            case _ => BadRequest
          }
      }
    }
  }











}

