package services

import mockws.MockWS
import models.Root
import util._
import org.scalatestplus.play.PlaySpec
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import org.mockito.Mockito._
import play.api.libs.json.Json
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import util.Fixtures
import org.mockito.Matchers.{eq => meq, _}
import play.api.mvc.Action
import play.api.test.{FakeRequest, Helpers}
import play.api.mvc.Results._
import scala.concurrent.duration._

import scala.concurrent.{Await, Future}

import services.AllLocationsSuccessResponse


class HasMetOfficeSpec extends PlaySpec with MockitoSugar {

  "Calling HasMetOffice.getLocations" should {

    "Return a AllLocationsSuccessResponse containing a Root model of all locations" in {

      val responseBody = Json.parse(Fixtures.exampleListOfLocationsResponse)

      val fakeService = MockWS {
        case ("GET", "/locations") => Action { Ok(responseBody) }
      }
      val result = fakeService.url("/locations").get()

      //val http = fakeService

      when(fakeService.url("/locations").get()) thenReturn {
        result
      }

      val service = new MetOfficeService(fakeService)

      val locations = Await.result(service.getLocations, 10 seconds)

      locations mustBe AllLocationsSuccessResponse(any())
    }
  }

    "Return a ExampleNotFound containing a status code of 404 when data is not found" in {

      val http: WSClient = mock[WSClient]



      val r : WSRequest = MockitoSugar.mock[WSRequest]
      when(http.url(any())) thenReturn r

      val wsResponse = WsResponse()

      when(r.get()) thenReturn eventualResponse


      val expectedResponse: Future[WSResponse] = Future.successful(mockResponse)

      //      when(mockResponse.status) thenReturn 404


      when()

      val fakeRequest = new MetOfficeService(http)
      val result = fakeRequest.getLocations

      result mustBe ExampleNotFound(404)



//      val requestBody = Json.parse(Fixtures.exampleListOfLocationsResponse)
//      val mockResponse = mock[WSResponse]
//      val expectedResponse: Future[WSResponse] = Future.successful(mockResponse)
//      val request = FakeRequest(Helpers.GET, "/fiveDayForecast").withJsonBody(requestBody)
//
//      val http: WSClient = mock[WSClient]
//
//      val someService = new MetOfficeService(http)
//
//      when(mockResponse.status) thenReturn 404
//      when(someService.getFiveDayForecast(any[String])) thenReturn Future.successful(ExampleNotFound(404))




//
//      test("should post something") {
//        val requestBody = <value>{UUID.randomUUID}</value>
//        val mockResponse = mock[WSResponse]
//        val expectedResponse: Future[WSResponse] = Future.successful(mockResponse)
//        val request = FakeRequest(Helpers.POST, "/posthere").withXmlBody(requestBody)
//
//        when(mockResponse.body).thenReturn("SOME_RESPONSE")
//        when(someService.processData(any[String])).thenReturn(expectedResponse)
//
//        val response: Future[Result] = call(controller.someWork , request)
//
//        whenReady(response) { response =>
//          assert(response.header.status == 200)
//        }
//      }



  }
}
