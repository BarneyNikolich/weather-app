package config

object WeatherSerivceUrls {

  private val API_KEY = "baff2146-ee96-429f-b077-efc1429e763a"

  private val metOfficeHost = "http://datapoint.metoffice.gov.uk/public/data"

  //API calls
  lazy val checkIfExistsUrl = s"$metOfficeHost/val/wxfcs/all/json/capabilities?res=daily&key=" + API_KEY
  lazy val listOfLocationsUrl = s"$metOfficeHost/val/wxfcs/all/json/sitelist?res=daily&key=" + API_KEY
  lazy val threeHourlyReportUrl = s"$metOfficeHost/val/wxfcs/all/json/3840?res=3hourly&key=" + API_KEY

}
