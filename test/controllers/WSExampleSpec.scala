package controllers

import models.Root
import services.{AllLocationsSuccessResponse, MetOfficeService}
import org.scalatestplus.play.PlaySpec
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.Result
import play.api.test.{FakeHeaders, FakeRequest}
import play.test.Helpers
import util.Fixtures

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
/**
  * Created by barn on 28/07/16.
  */
class WSExampleSpec extends PlaySpec with MockitoSugar {

  "Calling WSExampleController.getAllLocations" should {

    "return 200 when making a successful call" in {

      val mockMetService = mock[MetOfficeService]

      when(mockMetService.getLocations) thenReturn {
        Future.successful(AllLocationsSuccessResponse(Json.parse(Fixtures.exampleListOfLocationsResponse).as[Root]))
      }

//      val resu


//      def status(res : Result): Int = {
//        res.header.status
//      }
//
//      def status(res: Future[Result])(implicit timeout: Duration): Int = status(Await.result(res, timeout))


//
//      val http = mock[WSClient]
//      val metOfficeService = new MetOfficeService(http)
//
//      val exampleJsonResponse = Json.parse(Fixtures.exampleListOfLocationsResponse).as[Root]
//      when(metOfficeService.getLocations) thenReturn Future.successful(AllLocationsSuccessResponse(exampleJsonResponse))
//
//      val r = metOfficeService.getLocations
////      val r = metOfficeService.getLocations.(FakeRequest("GET", "/locations"))
//
////      r mustBe Future.successful(AllLocationsSuccessResponse(exampleJsonResponse))
//
//      val fakeRequest = FakeRequest(Helpers.GET, controllers.routes.WSExampleController.getAllLocations().url)
////
//      val result = controllers.routes.WSExampleController.getAllLocations()(fakeRequest).result.value.get
//
//







    }


  }





}
