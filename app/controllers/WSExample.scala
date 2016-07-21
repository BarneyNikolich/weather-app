package controllers

import java.util.concurrent.TimeoutException
import javax.inject.Inject

import play.api.libs.ws.{WSClient, WSClientConfig}
import play.api.mvc.{Action, Controller, Result}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import config.WeatherSerivceUrls._
import models._
import play.api.libs.json.JsValue

sealed trait MetOfficeResponse
case class ExampleBodyResponse(locations: Locations) extends MetOfficeResponse
case class ExampleNotFound(code: Int) extends MetOfficeResponse
case class ExampleTimeOut(error: String) extends MetOfficeResponse



class WSExample @Inject() (ws: WSClient) extends Controller {

  def index = Action {
    Ok(views.html.index())
  }
//
//  def callApiDirectly = Action.async {
//    {
//      ws.url(threeHourlyReportUrl).get() map {
//        response =>
//          response.status match {
//            case 200 => Ok("Successful 200 Get Request!" + response.body)
//            case 404 => NotFound(response.body)
//
//          }
//      } recover {
//          case t: TimeoutException => Ok("TIMEOUT!!")
//      }
//
//    }
//  }

  def callApiReturnRsponse(url: String): Future[MetOfficeResponse] = {
    {
      ws.url(url).get() map {
        response =>
          response.status match {
            case 200 =>
              val locationsList = Locations.fromJsonToLocationsObject(response.json)
              ExampleBodyResponse(locationsList)
            case 404 => ExampleNotFound(response.status)
          }
      } recover {
        case t: TimeoutException => ExampleTimeOut(t.getLocalizedMessage)
      }
    }
  }


//  def get3Hourly = Action.async {
//    {
//      callApiReturnRsponse(threeHourlyReportUrl).map {
//        response =>
//          response match {
//            case ExampleBodyResponse(body) => Ok(body)
//            case ExampleTimeOut(t) => Ok(t)
//            case _ => Ok("TIMEOUT")
//          }
//      }
//    }
//  }

  def getAllLocations = Action.async {
    {
      callApiReturnRsponse(listOfLocationsUrl).map {
        response =>
          response match {
            case ExampleBodyResponse(locationsList) =>
              val hydeExist = locationsList.doesTownExist("Hyde")
              if(hydeExist) {
                Ok("HYDE EXISTS WOO")
              } else BadRequest


            case ExampleTimeOut(t) => Ok(t)
            case _ => Ok("TIMEOUT")
          }
      }
    }
  }












}

