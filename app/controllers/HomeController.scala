package controllers

import java.util
import java.util.ArrayList
import javax.inject._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.i18n.Messages

import main.{MetroCarsUtils, StartUtils, PassengerUtils}
import models.{MetroCar, Passenger, Station}
import play.api.mvc._
import rx.lang.scala.Observable
import scala.concurrent.duration._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private var schedule: Int = 0
  private var flagPass: Boolean = false
  private var flagMetro: Boolean = false


  def index = Action {
    if (flagPass == false) {
      createThread
      println("Observable created for passengers...")
      val o = Observable.interval(600000 millis)
      o.subscribe(n => PassengerUtils.readPassengersFile())
      flagPass = true
    }

    Ok(views.html.index("Bienvenido a SDM - Bogotá TransmiMetro", "TransmiMetro", null, 0, null, null))
  }

  def passengers = Action {
    StartUtils.countPassengersPerStation()
    var stations = StartUtils.getListStations()
    Ok(views.html.index("Passengers", "The amount of passenger at every station", stations, 1, null, null))
  }

  def location = Action {
    if (flagMetro == false) {
      println("Observable created for schedules...")
      val o = Observable.interval(600000 millis)
      o.subscribe(x => MetroCarsUtils.readSchedulesFile())
      flagMetro = true
    }
    var carsl = MetroCarsUtils.searchCurrentStation()
    Ok(views.html.index("Location", "Metrocar location", null, 2, carsl, null))
  }

  def metro = Action {
    MetroCarsUtils.countPassengersInAMetroCar()
    var cars: util.ArrayList[MetroCar] = MetroCarsUtils.getSchedules()
    Ok(views.html.index("Each Metrocar", "The number of passengers in each Metrocar", null, 3, null, cars))
  }

  def densityPassenger = Action {
    PassengerUtils.densityPassenger("Carrera 80")
    Ok(views.html.index("Report", "Density of Passenger", null, 4, null, null))
  }

  def createThread(): Unit = {
    new Thread(new Runnable() {
      def run() {
        MetroCarsUtils.readSchedulesFile()
        PassengerUtils.readPassengersFile()
      }
    }).start()
  }
}
