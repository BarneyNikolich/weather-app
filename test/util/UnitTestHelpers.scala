package util

import scala.concurrent.duration._
import play.api.mvc.Result

import scala.concurrent.Future


trait UnitTestHelpers {

  //    Local Await method - calls Scala's Await, just abstracts it locally. Returns a result out of a Future
  def Await(result: Future[Result]): Result = {
    scala.concurrent.Await.result(result, 10 seconds)
  }

  def Status(result: Result): Int = {
    result.header.status
  }

  def Status(result: Future[Result]) : Int = {
    Await(result).header.status
  }

}
