package controllers

import java.util.concurrent.TimeoutException
import javax.inject.Inject

import play.api.libs.ws.{WSClient, WSClientConfig}
import play.api.mvc.{Action, Controller, Result}
import services._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import config.WeatherSerivceUrls._
import models._
import play.api.libs.json.JsValue


class WSExample @Inject() (ws: WSClient, metOfficeService: MetOfficeService) extends Controller {

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
      metOfficeService.threeHourly(351985.toString).map {
        response =>
          println(s"-----------------------------------     ${response.json}")
      }

      metOfficeService.showGetResult.map {
        response =>
          response match {
            case ExampleBodyResponse(locationsList) =>
              val hydeExist = locationsList.doesTownExist("Hyde")
              val locationObject = locationsList.getTown("Hyde")
              if(hydeExist) {
                Ok("HYDE EXISTS WOO" + locationObject.map(_.id))
              } else BadRequest


            case ExampleTimeOut(t) => InternalServerError
            case _ => Ok("TIMEOUT")
          }
      }
    }
  }












}

