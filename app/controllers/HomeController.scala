package controllers

import java.util
import java.util.ArrayList
import javax.inject._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

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

  private var cars:     ArrayList[MetroCar] = StartUtils.getListMetroCars()
  private var stations: ArrayList[Station] = StartUtils.getListStations()
  private var schedule: Int = 0
  private var flag: Boolean = false

  def index = Action {
    if(flag == false) {
      println("Observable created...")
      val o = Observable.interval(60000 millis)
      o.subscribe(n => PassengerUtils.readPassengersFile())
      flag = true
    }

    Ok(views.html.index("Bienvenido a SDM - Bogotá TransmiMetro", "TransmiMetro", null, 0, null, null))
  }

  def passengers = Action {
    Ok(views.html.index("Passengers", "The amount of passenger at every station", stations, 1, null, null))
  }

  def location = Action {
    var carsl = MetroCarsUtils.searchCurrentStation()
    Ok(views.html.index("Location", "Metrocar location", null, 2, carsl, null))
  }

  def metro = Action {
    Ok(views.html.index("Each Metrocar", "The number of passengers in each Metrocar", null, 3, null, cars))
  }
}
