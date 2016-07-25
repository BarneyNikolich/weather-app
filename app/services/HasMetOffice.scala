package services

import java.util.concurrent.TimeoutException
import javax.inject.Inject

import connectors.MetOfficeConnector
import models.Locations
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

sealed trait MetOfficeResponse
case class ExampleBodyResponse(locations: Locations) extends MetOfficeResponse
case class ExampleNotFound(code: Int) extends MetOfficeResponse
case class ExampleTimeOut(error: TimeoutException) extends MetOfficeResponse

class MetOfficeService @Inject() (metOfficeConnector: MetOfficeConnector) {

  def showGetResult: Future[MetOfficeResponse] = {

    metOfficeConnector.getAllLocations map {
      response =>

        response.status match {
          case 200 => ExampleBodyResponse(response.json.as[Locations])
          case 404 => ExampleNotFound(response.status)
        }

    } recover {
      case t: TimeoutException => ExampleTimeOut(t)
    }
  }

  def threeHourly(id: String) = {
    metOfficeConnector.getThreeHourlyReport(id) map {
      response =>
        response
    }
  }

}
