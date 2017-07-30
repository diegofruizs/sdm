package main

import java.util
import java.util.{ArrayList, Calendar}

import models.{MetroCar, Passenger, Schedule, Station}
import rx.lang.scala.Observable

import scala.concurrent.duration._

object Inicio {

  def main(args: Array[String]) {

    // println(StartUtils.determinateRoute("Carrera 50", "Narino"))
    // var st = new Station(15, "Carrera 80", null, false, null, null)

    PassengerUtils.readPassengersFile()
    // println(PassengerUtils.searchMetroCarToGetOn_v2("Carrera 80", "09:32 30-07-2017", "Calle 45"))
  }
}
