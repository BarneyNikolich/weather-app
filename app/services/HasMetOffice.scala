package services

import java.util.concurrent.TimeoutException
import javax.inject.Inject

import config.WeatherSerivceUrls
import models.{FiveDayReportRoot, Root}
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


sealed trait MetOfficeResponse
case class AllLocationsSuccessResponse(locations: Root) extends MetOfficeResponse
case class FiveDayForecastSuccessResponse(report: FiveDayReportRoot) extends MetOfficeResponse
case class ExampleNotFound(code: Int) extends MetOfficeResponse
case class ExampleTimeOut(error: TimeoutException) extends MetOfficeResponse

class MetOfficeService @Inject() (http: WSClient) {

  def getLocations: Future[MetOfficeResponse] = {

//  Call 1: Making a call to ws.url so need to mock this call in testing -> Returns a WSRequest
    val r = http.url(WeatherSerivceUrls.listOfLocationsUrl)

//  Call 2: Making a call to .get -> Returns a WSResponse
    r.get map {
      response =>
        println(response)

        response.status match {
          case 200 => AllLocationsSuccessResponse(response.json.as[Root])
          case 404 => ExampleNotFound(response.status)
        }

    } recover {
      case t: TimeoutException => ExampleTimeOut(t)
    }
  }

  /**
    * Set to call with Wallasey ID
    */

  def getFiveDayForecast(id: String): Future[MetOfficeResponse] = {

    http.url(WeatherSerivceUrls.fiveDayForecast(id)).get map {
      fiveDayForecast =>

        fiveDayForecast.status match {
          case 200 => FiveDayForecastSuccessResponse(fiveDayForecast.json.as[FiveDayReportRoot])
          case 404 => ExampleNotFound(fiveDayForecast.status)
        }

    } recover {
      case t: TimeoutException => ExampleTimeOut(t)
    }
  }


}
