package models

import play.api.libs.json._

case class Locations(locationNames: Seq[String]) {

  def doesTownExist(name: String): Boolean = {
    locationNames.exists(_ == name)
  }
}

object Locations {
    implicit val formats = Json.format[Locations]



  def fromJsonToLocationsObject(location: JsValue): Locations = {
    val locationNames = location \ "Locations" \ "Location"
    val listOfNames = (locationNames \\ "name").map(_.as[String])
    Locations(listOfNames)
  }

}

//class LocationsTest (elevation: String,
//                     id: String,
//                     latitude: String,
//                     longitude: String,
//                     name: String,
//                     region: String,
//                     unitaryAuthArea: String)
//
//
//object Address {
//  val formats = Json.format[LocationsTest]
//  implicit val writes: Writes[LocationsTest] = formats
//}