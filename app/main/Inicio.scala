package main

import java.util
import java.util.{ArrayList, Calendar}

import models.{MetroCar, Passenger, Schedule, Station}
import rx.lang.scala.Observable

import scala.concurrent.duration._

import play.api.libs.json._

object Inicio {

  def main(args: Array[String]) {

    MetroCarsUtils.readSchedulesFile()

    /*var list:ArrayList[Passenger] = new ArrayList[Passenger]()
    list.add(new Passenger(1,
      "10:50:56 30-07-2017",
      StartUtils.searchStation("Carrera 80"),
      "11:15:56 30-07-2017",
      StartUtils.searchStation("Carrera 80"),
      null, null, null))*/

    // PassengerUtils.readPassengersFile()
    // PassengerUtils.densityPassenger("Carrera 80")

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

    case class Location(lat: Double, long: Double)
    case class Resident(name: String, age: Int, role: Option[String])
    case class Place(name: String, location: Location, residents: Seq[Resident])

    implicit val locationWrites = new Writes[Location] {
      def writes(location: Location) = Json.obj(
        "lat" -> location.lat,
        "long" -> location.long
      )
    }

    implicit val residentWrites = new Writes[Resident] {
      def writes(resident: Resident) = Json.obj(
        "name" -> resident.name,
        "age" -> resident.age,
        "role" -> resident.role
      )
    }

    implicit val placeWrites = new Writes[Place] {
      def writes(place: Place) = Json.obj(
        "name" -> place.name,
        "location" -> place.location,
        "residents" -> place.residents
      )
    }

    val place = Place(
      "Watership Down",
      Location(51.235685, -1.309197),
      Seq(
        Resident("Fiver", 4, None),
        Resident("Bigwig", 6, Some("Owsla"))
      )
    )

    val json = Json.toJson(place)

    println(json)
  }
}
