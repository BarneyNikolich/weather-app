package services

import java.util.concurrent.TimeoutException
import javax.inject.Inject

import connectors.MetOfficeConnector
import models.Root

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

sealed trait MetOfficeResponse
case class MetOfficeSuccessResponse(locations: Root) extends MetOfficeResponse
case class ExampleNotFound(code: Int) extends MetOfficeResponse
case class ExampleTimeOut(error: TimeoutException) extends MetOfficeResponse

class MetOfficeService @Inject() (metOfficeConnector: MetOfficeConnector) {

  def getLocations: Future[MetOfficeResponse] = {

    metOfficeConnector.getAllLocations map {
      response =>

        response.status match {
          case 200 => MetOfficeSuccessResponse(response.json.as[Root])
          case 404 => ExampleNotFound(response.status)
        }

    } recover {
      case t: TimeoutException => ExampleTimeOut(t)
    }
  }

  /**
    * AMMEND THE JSON TO READ A 5 DAY FORECAST! ADD A MODEL
    * @param id
    * @return
    */

  def getFiveDayForecast(id: String): Future[MetOfficeResponse] = {
    metOfficeConnector.getAllLocations map {
      fiveDayForecast =>

        fiveDayForecast.status match {
          case 200 => MetOfficeSuccessResponse(fiveDayForecast.json.as[Root])
          case 404 => ExampleNotFound(fiveDayForecast.status)
        }

    } recover {
      case t: TimeoutException => ExampleTimeOut(t)
    }
  }


}
