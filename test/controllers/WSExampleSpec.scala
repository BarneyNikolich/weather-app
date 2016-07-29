package controllers

import models.Root
import services.{AllLocationsSuccessResponse, MetOfficeService}
import org.scalatestplus.play._
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import play.api.i18n.MessagesApi
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.Result
import play.api.test._
import play.test.Helpers._
import util.Fixtures

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
/**
  * Created by barn on 28/07/16.
  */
class WSExampleSpec extends PlaySpec with MockitoSugar {

  "Calling WSExampleController.getAllLocations" should {

    "return 200 when making a successful call" in {

      val mockMetService = mock[MetOfficeService]
      val messagesMock = mock[MessagesApi]

      when(mockMetService.getLocations) thenReturn {
        Future.successful(AllLocationsSuccessResponse(Json.parse(Fixtures.exampleListOfLocationsResponse).as[Root]))
      }

      val controller = new WSExampleController(mockMetService, messagesMock)//.getAllLocations().apply(FakeRequest())

      val result: Future[Result] = controller.getAllLocations().apply(FakeRequest())

      val req = Await.result(result, 10 seconds)

      req.header.status mustBe(200)
//      val g: String = contentAsString(req)
      //a.body. mustBe(Json.parse(Fixtures.exampleListOfLocationsResponse).as[Root])

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
