package models

import play.api.libs.json._

/**
  * A case class for a specific town
*/

case class Town(
                 elevation: Option[String],
                 id: String,
                 latitude: String,
                 longitude: String,
                 name: String,
                 region: Option[String],
                 unitaryAuthArea: Option[String])

object Town {
  implicit val formats = Json.format[Town]
}

case class Location(Location: Seq[Town]) {

  def townExists(queryName: String): Boolean = {
    Location.exists(town => town.name == queryName)
  }

//  def getTown(queryTown: String) {
//    Location.
//  }
}

object Location {
  implicit val formats = Json.format[Location]
}

case class Root(Locations: Location)

object Root {
  implicit val formats = Json.format[Root]
}













//case class ListOfTowns(Location: Seq[Town])
//
//object ListOfTowns {
//  implicit val formats = Json.format[ListOfTowns]
//}
//
//case class Locations(Locations: ListOfTowns) {
//
//  def doesTownExist(name: String): Boolean = {
//    Locations.Location.exists(a => a.name == name)
//  }
//
//  def getTown(name: String): Option[Town] = {
//    Locations.Location.find(a => a.name == name)
//  }
//}
//
//object Locations {
//    implicit val formats = Json.format[Locations]



  /*def fromJsonToLocationsObject(location: JsValue): Locations = {
    val locationNames = location \ "Locations" \ "Location"
    val listOfNames = (locationNames \\ "name").map(_.as[String])
    Locations(listOfNames)
  }*/





