package services

import java.util.concurrent.TimeoutException
import javax.inject.Inject

import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global

sealed trait MetOfficeResponse
case class ExampleBodyResponse(body: String) extends MetOfficeResponse
case class ExampleNotFound(code: Int) extends MetOfficeResponse
case class ExampleTimeOut(error: TimeoutException) extends MetOfficeResponse

trait h {

  val a : MetOfficeService

  class MetOfficeService(ws: WSClient) {

    lazy val API_KEY = "baff2146-ee96-429f-b077-efc1429e763a"


    def showGetResult {
      val testUrl = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/3840?res=3hourly&key=" + API_KEY

      ws.url(testUrl).get() map {
        response =>

          response.status match {
            case 200 => ExampleBodyResponse(response.body)
            case 404 => ExampleNotFound(response.status)
          }

      } recover {
        case t: TimeoutException => ExampleTimeOut(t)
      }
    }
  }

}

