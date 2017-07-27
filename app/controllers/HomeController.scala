package controllers

import java.util
import java.util.ArrayList
import javax.inject._
import main.Inicio

import models.{MetroCar, Passenger, Station}
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page with a station message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action {
    Ok(views.html.index("Bienvenido a SDM - Bogotá TransmiMetro", "TransmiMetro", null, 0, null, null))
  }

  def passengers = Action {
    var stations: ArrayList[Station] = new ArrayList[Station]()
    stations = new Station().getStations()
    Ok(views.html.index("Passengers", "The amount of passenger at every station", stations, 1, null, null))
  }

  def location = Action {
    var cars: ArrayList[MetroCar] = new ArrayList[MetroCar]()
    cars = new MetroCar().getMetroCars()
    Ok(views.html.index("Location", "MetroCar location", null, 2, cars, null))
  }

  def metro = Action {
    var cars: ArrayList[MetroCar] = new ArrayList[MetroCar]()
    //cars = new MetroCar().getMetroCars()
    cars = Inicio.constuirMetroCars()
    Ok(views.html.index("Each Metro", "The number of passengers in each metro-car", null, 3, null, cars))
  }

}
