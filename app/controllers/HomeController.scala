package controllers

import java.util
import java.util.ArrayList
import javax.inject._

import main.{MetroCarsUtils, StartUtils}
import models.{MetroCar, Passenger, Station}
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private var cars: ArrayList[MetroCar] = StartUtils.getListMetroCars()
  private var stations: ArrayList[Station] = StartUtils.getListStations()
  private var schedule: Int = 0

  def index = Action {
    Ok(views.html.index("Bienvenido a SDM - Bogotá TransmiMetro", "TransmiMetro", null, 0, null, null))
  }

  def passengers = Action {
    Ok(views.html.index("Passengers", "The amount of passenger at every station", stations, 1, null, null))
  }

  def location = Action {
    var carsl: ArrayList[MetroCar] = new ArrayList[MetroCar]()
    carsl = MetroCarsUtils.searchCurrentStation()
    Ok(views.html.index("Location", "Metrocar location", null, 2, carsl, null))
  }

  def metro = Action {
    Ok(views.html.index("Each Metrocar", "The number of passengers in each Metrocar", null, 3, null, cars))
  }
}
