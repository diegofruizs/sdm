package main
import java.util.ArrayList

import models.{MetroCar, Passenger, Station}

object Inicio {
  def main(args: Array[String]) {
    println("Iniciando creación de objetos de SDM")

    // DEfinimos un lista de pasajeros e inicializamos en cero
    val listPassengers: ArrayList[Passenger] = new ArrayList[Passenger]()

    // Creamos dos objetos de tipo estación
    val portalAmericas = new Station(1, "Portal americas", listPassengers, true)
    val calle1 = new Station(2, "Calle 1", listPassengers, true)

    println(portalAmericas.name)
    println(portalAmericas.stationHub)

    val metro1 = new MetroCar(1, 900, portalAmericas, listPassengers)

    var passenger1 = new Passenger(100, "7:00:00 a. m.", portalAmericas, null, calle1, metro1)
    var passenger2 = new Passenger(101, "7:00:00 a. m.", portalAmericas, null, calle1, metro1)

    listPassengers.add(passenger1)
    listPassengers.add(passenger2)

    portalAmericas.amountPassengers=listPassengers
    calle1.amountPassengers=passenger1.getPassengers()

    println("Pasajeros en Calle1: "+calle1.amountPassengers.size)
    println("Pasajeros en Portal: "+portalAmericas.amountPassengers.size)
    println(listPassengers.size)

    if (listPassengers.size > 0) {
      for(a <- 0 to listPassengers.size()-1){
        println(listPassengers.get(a).destination.name)
        println(listPassengers.get(a).entranceStation.name)
        println(listPassengers.get(a).entranceTime)
        println(listPassengers.get(a).metroCar.id)
        println("****************")
      }
    }
  }
}
