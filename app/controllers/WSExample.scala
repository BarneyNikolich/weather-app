package controllers


import javax.inject.Inject

import play.api.libs.ws._
import play.api.mvc.{Result, Action, Controller}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class WSExample @Inject() (ws: WSClient) extends Controller() {

  lazy val API_KEY = "baff2146-ee96-429f-b077-efc1429e763a"

  def index = Action {
    Ok(views.html.index())
  }


  def showGetResult = Action.async {

    val testUrl = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/3840?res=3hourly&key="+API_KEY

    val result: Future[Result] = ws.url(testUrl).get() map {
      response =>
        Ok(response.body)
    }
    result
  }


}