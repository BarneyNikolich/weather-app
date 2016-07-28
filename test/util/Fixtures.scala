package util

import org.scalatest.mock.MockitoSugar
import play.api.libs.ws.WSClient
import services.MetOfficeService

import scala.io.Source

trait MetOfficeFixtures {

  def exampleListOfLocationsResponse = Source.fromInputStream(getClass.getResourceAsStream("/MetOfficeResponse/listOfLocation.json"), "UTF-8").mkString
  def exampleFiveDayForecastResponse = Source.fromInputStream(getClass.getResourceAsStream("/MetOfficeResponse/fiveDayForecast.json"), "UTF-8").mkString

}

object Fixtures extends MetOfficeFixtures {


  def buildFakeMetOfficeService = {
    val http: WSClient = MockitoSugar.mock[WSClient]
    new MetOfficeService(http)
  }

}