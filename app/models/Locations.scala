package models

import play.api.libs.json._

case class LocationsTest (elevation: Option[String],
                          id: String,
                          latitude: String,
                          longitude: String,
                          name: String,
                          region: Option[String],
                          unitaryAuthArea: Option[String])

object LocationsTest {
  implicit val formats = Json.format[LocationsTest]
}

case class InnerLocations(Location: Seq[LocationsTest])

object InnerLocations {
  implicit val formats = Json.format[InnerLocations]
}

case class Locations(Locations: InnerLocations) {

  def doesTownExist(name: String): Boolean = {
    Locations.Location.exists(a => a.name == name)
  }

  def getTown(name: String): Option[LocationsTest] = {
    Locations.Location.find(a => a.name == name)
  }
}

object Locations {
    implicit val formats = Json.format[Locations]



  /*def fromJsonToLocationsObject(location: JsValue): Locations = {
    val locationNames = location \ "Locations" \ "Location"
    val listOfNames = (locationNames \\ "name").map(_.as[String])
    Locations(listOfNames)
  }*/

}




//object Address {
//  val formats = Json.format[LocationsTest]
//  implicit val writes: Writes[LocationsTest] = formats
//}