package models

import play.api.data._
import play.api.data.Forms._

case class WeatherRequest(town: String)

object WeatherRequest {
  val weatherForm = Form (
    mapping (
      "town" -> nonEmptyText.verifying("Must not contain numbers!", c => c.matches("[a-zA-Z]*"))
        .verifying("Must be between 2 and 10 characters", s => s.matches("[a-zA-Z]{2,10}"))
    ) (WeatherRequest.apply) (WeatherRequest.unapply)
  )
}


