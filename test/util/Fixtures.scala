package util

import org.scalatest.mock.MockitoSugar
import play.api.libs.ws.WSClient
import services.MetOfficeService

import scala.io.Source

trait MetOfficeFixtures {

  //def exampleListOfLocationsResponse = Source.fromInputStream(getClass.getResourceAsStream("test/resources/MetOfficeResponses/listOfLocation.json"), "UTF-8").mkString
  def exampleListOfLocationsResponse = scala.io.Source.fromFile("test/resources/MetOfficeResponses/listOfLocation.json").mkString
  //def exampleFiveDayForecastResponse = Source.fromInputStream(getClass.getResourceAsStream("test/resources/MetOfficeResponses/fiveDayForecast.json"), "UTF-8").mkString

}

object Fixtures extends MetOfficeFixtures {


  def buildFakeMetOfficeService = {
    val http: WSClient = MockitoSugar.mock[WSClient]
    new MetOfficeService(http)
  }

}