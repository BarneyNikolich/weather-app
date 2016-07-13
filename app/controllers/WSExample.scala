package controllers


import javax.inject.Inject
import scala.concurrent.Future
import scala.concurrent.duration._

import play.api.mvc._
import play.api.libs.ws._
import play.api.http.HttpEntity

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.ExecutionContext

class WSExample @Inject() (ws: WSClient) extends Controller {

  lazy val API_KEY = "baff2146-ee96-429f-b077-efc1429e763a"


  //Calling all locations
  val testUrl = "http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/json/3840?res=3hourly&key="+API_KEY
  val request: WSRequest = ws.url(testUrl)

}