package main

import java.util
import java.util.{ArrayList, Calendar}

import models.{MetroCar, Passenger, Schedule, Station}
import rx.lang.scala.Observable

import scala.concurrent.duration._

object Inicio {

  def main(args: Array[String]) {

    var list:ArrayList[Passenger] = new ArrayList[Passenger]()
    list.add(new Passenger(1,
      "10:50:56 30-07-2017",
      StartUtils.searchStation("Carrera 80"),
      "11:15:56 30-07-2017",
      StartUtils.searchStation("Carrera 80"),
      null, null, null))

    // PassengerUtils.readPassengersFile()
    PassengerUtils.densityPassenger("Carrera 80")

    /*
    for test of amount of passengers per station
    PassengerUtils.readPassengersFile()
    StartUtils.countPassengersPerStation()
    var stations = StartUtils.getListStations()

    for (a <- 0 until stations.size() - 1) {
      println("Estacion: " + stations.get(a).name + " Con " + stations.get(a).amountPassengers + " Pasajeros")
    }*/

    // for test count passenger per metroCar
    //MetroCarsUtils.countPassengersInAMetroCar()


    // println(PassengerUtils.searchMetroCarToGetOn_v2("Carrera 50", "09:32 30-07-2017", "Narino"))

  }
}
