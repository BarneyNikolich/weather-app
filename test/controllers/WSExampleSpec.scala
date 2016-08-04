package controllers

import models.{FiveDayReportRoot, Root}
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import play.api.i18n.MessagesApi
import play.api.libs.json.Json
import play.api.mvc.{AnyContentAsEmpty, Result}
import play.api.test._
import services.{AllLocationsSuccessResponse, FiveDayForecastSuccessResponse, MetOfficeService, NotFoundResponse}
import util._
import play.api.http.Status._
import scala.concurrent.Future

class WSExampleSpec extends PlaySpec with MockitoSugar {

  trait LocalSetup extends UnitTestHelpers {
    val mockMetService = mock[MetOfficeService]
    val mockMessages = mock[MessagesApi]

    val allLocationsSuccessResponse = AllLocationsSuccessResponse(Json.parse(Fixtures.exampleListOfLocationsResponse).as[Root])

    val controller = new WSExampleController(mockMetService, mockMessages)
  }


  "Calling WSExampleController.getWeatherData" should {

    "return 200 when AllLocationsSuccessResponse is returned from MetOfficeService" in new LocalSetup {

      when(mockMetService.getLocations) thenReturn {
        Future.successful(AllLocationsSuccessResponse(Json.parse(Fixtures.exampleListOfLocationsResponse).as[Root]))
      }

      when(mockMetService.getFiveDayForecast("3044")) thenReturn {
        Future.successful(FiveDayForecastSuccessResponse(Json.parse(Fixtures.exampleFiveDayForecastResponse).as[FiveDayReportRoot]))
      }

      val r = controller.getWeatherData("Test Town")(FakeRequest("GET", "/", FakeHeaders(), AnyContentAsEmpty))
      Status(r) mustBe OK
    }

    "return a 400 when MetOffice returns a ExampleNotFoundResponse for getAllLocations call" in new LocalSetup {

      when(mockMetService.getLocations) thenReturn Future.successful(NotFoundResponse(404))

      val r = controller.getAllLocations().apply(FakeRequest())
      Status(r) mustBe BAD_REQUEST
    }

    "return 400 when MetOffice returns AllLocationsSuccessResponse for getAllLocationsCall but NotFoundResponse for getFiveDayForecast" in new LocalSetup {

      when(mockMetService.getLocations) thenReturn Future.successful(allLocationsSuccessResponse)
      when(mockMetService.getFiveDayForecast("3044")) thenReturn Future.successful(NotFoundResponse(404))

      val r = controller.getWeatherData("Test Town").apply(FakeRequest())
      Status(r) mustBe BAD_REQUEST
    }

    "return a 400 when MetOffice returns AllLocationsSuccessResponse but town doesn't exist in list of Locations" in new LocalSetup {
      when(mockMetService.getLocations) thenReturn Future.successful(allLocationsSuccessResponse)

      val r = controller.getWeatherData("Timbooktwo").apply(FakeRequest())
      Status(r) mustBe BAD_REQUEST

    }
  }


  "Calling WSExampleController.getAllLocations" should {

    "return 200 when a AllLocationsSuccessResponse is returned from MetOfficeService" in new LocalSetup{

      when(mockMetService.getLocations) thenReturn {
        Future.successful(AllLocationsSuccessResponse(Json.parse(Fixtures.exampleListOfLocationsResponse).as[Root]))
      }

      val result: Future[Result] = controller.getAllLocations()(FakeRequest("GET", "/", FakeHeaders(), AnyContentAsEmpty))

      Status(result) mustBe(200)
    }

  "return 400 when a ExampleNotFoundResponse is returned from MetOfficeService" in new LocalSetup {

    when(mockMetService.getLocations) thenReturn Future.successful(NotFoundResponse(404))

    val r = controller.getAllLocations()(FakeRequest("GET", "/", FakeHeaders(), AnyContentAsEmpty))

    Status(r) mustBe 400

  }






  }







}
