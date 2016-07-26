package connectors

import config.WeatherSerivceUrls
import javax.inject.Inject

import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future

class MetOfficeConnector @Inject() (wsClient: WSClient) {

  def getAllLocations: Future[WSResponse] = {
    wsClient.url(WeatherSerivceUrls.listOfLocationsUrl).get
  }

  def getFiveDayForecast(id: String): Future[WSResponse] = {
    wsClient.url(WeatherSerivceUrls.fiveDayForecast(id)).get
  }

  def getThreeHourlyReport(id: String): Future[WSResponse] = {
    wsClient.url(WeatherSerivceUrls.threeHourlyReportUrl(id)).get
  }

}
