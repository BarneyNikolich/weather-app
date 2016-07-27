package models

import play.api.libs.json.Json



case class Rep(
  D: Option[String],
  Gn: Option[String],
  Gm: Option[String],
  Hm: Option[String],
  Hn: Option[String],
  PPd: Option[String],
  PPn: Option[String],
  S: Option[String],
  V: Option[String],
  Nm: Option[String],
  FNm: Option[String],
  Dm: Option[String],
  FDm: Option[String],
  W: Option[String],
  U: Option[String],
  $: Option[String]
)

case object Rep {
  implicit val formats = Json.format[Rep]
}

case class DvPeriod(
                     `type`: String,
                     value: String,
                     Rep: Seq[Rep]
                   )

object DvPeriod {
  implicit val formats = Json.format[DvPeriod]
}

object LocationFiveDay {
  implicit val formats = Json.format[LocationFiveDay]

}
case class WeatherData(dataDate: String, `type`: String, Location: LocationFiveDay)

object WeatherData {
  implicit val formats = Json.format[WeatherData]
}


case class Period(`type`: String, value: String, Rep: Rep)

case object Period {
  implicit val formats = Json.format[Period]
}


case class LocationFiveDay(
   i: String,
   lat: String,
   lon: String,
   name: String,
   country: String,
   continent: String,
   elevation: String,
   Period: Seq[DvPeriod]
)

case class Param(name: String, units: String, $: String)
object Param {
  implicit val formats = Json.format[Param]
}


case class ParamData(Param: Seq[Param])

object ParamData {
  implicit val formats = Json.format[ParamData]

}

case class DvData(dataDate: String, `type`: String, Location: LocationFiveDay)

object DvData {
  implicit val formats = Json.format[DvData]

}

case class Types(Wx: ParamData, DV: DvData)

case object Types {
  implicit val formats = Json.format[Types]
}



case class FiveDayReportRoot(SiteRep: Types)

object FiveDayReportRoot {
  implicit val formats = Json.format[FiveDayReportRoot]

}




