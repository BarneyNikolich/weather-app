package services

import models.Root
import util._
import org.scalatestplus.play.PlaySpec
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import org.mockito.Mockito._
import play.api.libs.json.Json
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import util.Fixtures

import scala.concurrent.Future


class HasMetOffice extends PlaySpec with MockitoSugar {

  "Calling HasMetOffice.getLocations" should {
//
//    "Return a AllLocationsSuccessResponse containing a Root model of all locations" in {
//
//      val fakeMetOfficeService = Fixtures.buildFakeMetOfficeService
//      val mockMetOfficeConnector = mock[MetOfficeService]
//
//
//
//      when(mockMetOfficeConnector.getLocations) thenReturn Future.successful(httpGetResponse)
//
//      val result = fakeMetOfficeService.getLocations
//
//      result mustBe httpGetResponse
//
//
//    }

    "Return a ExampleNotFound containing a status code of 404 when data is not found" in {

      val httpGetResponse = Fixtures.exampleListOfLocationsResponse

      val http: WSClient = MockitoSugar.mock[WSClient]

      val fakeRequest = new MetOfficeService(http)

      val mockMetOfficeConnector = new MetOfficeService(http)

//      when(http.url("any").get()) thenReturn Future.successful(200)
      val result = fakeRequest.getLocations

      result mustBe ExampleNotFound(404)


    }

  }
}
