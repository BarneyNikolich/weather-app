package controllers

import javax.inject.Inject
import play.api.mvc.{Action, Controller}
import services._
import scala.concurrent.ExecutionContext.Implicits.global

class WSExampleController @Inject()(metOfficeService: MetOfficeService) extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def getAllLocations() = Action.async {
    {
      metOfficeService.getLocations map {
        response =>
          response match {
            case AllLocationsSuccessResponse(locationsList) =>

//              val town = locationsList.Locations.Location.map(x => x.name)
              val ridingMillExisits = locationsList.Locations.townExists("Hexham")
              val haxham = locationsList.Locations.Location.find(x => x.name == "Hexham")
              val id = haxham.map(_.id)


//              if (h) {
                Ok("Does Hexham Mill exist? = " + ridingMillExisits + " Hexhams Json object is:  " + id)
//              } else BadRequest

            case ExampleTimeOut(t) => InternalServerError
            case _ => Ok("TIMEOUT")
          }
      }
    }
  }


  def getFiveDayForecast = Action.async {
    {
      metOfficeService.getFiveDayForecast(354059.toString) map {
        fiveDayForecast =>
          fiveDayForecast match {
            case FiveDayForecastSuccessResponse(forecast) =>
              val a = forecast.SiteRep.DV.Location.Period.map(_.Rep.map(_.PPd))


              Ok("Weather PPD for Wallasey " + a)
          }
      }

    }

  }











}

