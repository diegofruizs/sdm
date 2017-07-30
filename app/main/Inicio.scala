package main

import java.util.{ArrayList, Calendar}
import models.{MetroCar, Schedule, Station, Passenger}

import rx.lang.scala.Observable
import scala.concurrent.duration._

object Inicio {

  def main(args: Array[String]) {
    //val inter = LoadPassengerFiles.action("Tiger", "Elephant")
    //inter.interval(Duration(2000, MILLISECONDS))
    //inter.subscribe(art => println("--- Article ---\n" + art.substring(0, 125)))

    val o = Observable.interval(60000 millis)
    o.subscribe(n => PassengerUtils.readPassengersFile())

     waitFor(o)
  }

  def waitFor[T](obs: Observable[T]): Unit = {
    obs.toBlocking.toIterable.last
  }
}
