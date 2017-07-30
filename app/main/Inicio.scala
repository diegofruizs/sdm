package main

import java.util
import java.util.{ArrayList, Calendar}

import models.{MetroCar, Passenger, Schedule, Station}
import rx.lang.scala.Observable

import scala.concurrent.duration._

object Inicio {

  def main(args: Array[String]) {

    PassengerUtils.readPassengersFile()
    StartUtils.countPassengersPerStation()
    var stations = StartUtils.getListStations()

    for (a <- 0 until stations.size() - 1) {
      println("Estacion: " + stations.get(a).name + " Con " + stations.get(a).amountPassengers + " Pasajeros")
    }

    // println(PassengerUtils.searchMetroCarToGetOn_v2("Carrera 50", "09:32 30-07-2017", "Narino"))
  }
}
