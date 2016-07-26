package controllers

import javax.inject.Inject
import play.api.mvc.{Action, Controller}
import services._
import scala.concurrent.ExecutionContext.Implicits.global

class WSExample @Inject() (metOfficeService: MetOfficeService) extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def getAllLocations = Action.async {
    {
      metOfficeService.getLocations map {
        response =>
          response match {
            case MetOfficeSuccessResponse(locationsList) =>
              val h = locationsList.Locations.townExists("Hyde")
              val location = locationsList.Locations.Location.filter(x => x.name == "Hyde")
              val id = location.toString()


              if(h) {
                Ok("Hydes ID = " + id )
              } else BadRequest

            case ExampleTimeOut(t) => InternalServerError
            case _ => Ok("TIMEOUT")
          }
      }
    }
  }


  /**
    * ADD FIVE DAY FORECAST CALL TO CONTROLLER
    */
  //  def getThreeHourly = Action.async {
//    {
//      metOfficeService
//
//
//    }
//  }












}

