package main

import java.util
import java.util.{ArrayList, Calendar}

import models.{MetroCar, Passenger, Schedule, Station}
import rx.lang.scala.Observable

import scala.concurrent.duration._

object Inicio {

  def main(args: Array[String]) {

    PassengerUtils.readPassengersFile()
    PassengerUtils.searchMetroCarToGetOn()
    MetroCarsUtils.countPassengersInAMetroCar()
    var cars: util.ArrayList[MetroCar] = MetroCarsUtils.getSchedules()

    println("Size of cars " + cars.size())

    for (a <- 0 to cars.size() - 1) {
      if (cars.get(a).passengers.size() > 0) {
        println("En carro: "+ cars.get(a).id +" Hay "+cars.get(a).passengers.size())
      }
    }

  }
}
