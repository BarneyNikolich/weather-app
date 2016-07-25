package connectors

import config.WeatherSerivceUrls
import javax.inject.Inject

import play.api.libs.ws.{WSResponse, WSClient}

import scala.concurrent.Future

/**
 * Created by barn on 25/07/16.
 */
class MetOfficeConnector @Inject() (wsClient: WSClient) {

  def getAllLocations: Future[WSResponse] = {
    wsClient.url(WeatherSerivceUrls.listOfLocationsUrl).get
  }

  def getThreeHourlyReport(id: String) = {
    wsClient.url(WeatherSerivceUrls.threeHourlyReportUrl(id)).get
  }

}
