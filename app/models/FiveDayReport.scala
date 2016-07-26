package models

import play.api.libs.json.Json


case object LocationFiveDay {
  implicit val formats = Json.format[LocationFiveDay]
}

case class Rep(
                D: String,
                Gn: String,
                Hn: String,
                PPd: String,
                S: String,
                V: String,
                Dm: String,
                FDm: String,
                W: String,
                U: String,
                $: String
                )

case object Rep {
  implicit val formats = Json.format[Rep]
}



case class Period(`type`: String, value: String, Rep: Rep)

case object Period {
  implicit val formats = Json.format[Period]
}

case class WeatherData(dataDate: String, `type`: String, Location: LocationFiveDay)

case object WeatherData {
  implicit val formats = Json.format[WeatherData]

}


case class LocationFiveDay(
                     i: String,
                     lat: String,
                     lon: String,
                     name: String,
                     country: String,
                     continent: String,
                     elevation: String
                     )


case class Param(name: String, units: String, $: String)

case object Param {
  implicit val formats = Json.format[Param]
}





case class Types(Wx: Param, DV: Param)

case object Types {
  implicit val formats = Json.format[Types]
}



case class FiveDayReportRoot(SiteRep: Types)

object FiveDayReportRoot {
  implicit val formats = Json.format[FiveDayReportRoot]

}




