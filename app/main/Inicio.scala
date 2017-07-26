package main

import models.{MetroCar, Passenger, Station}

object Inicio {
  def main(args: Array[String]) {
    println("Iniciando creación de objetos de SDM")

    // DEfinimos un lista de pasajeros e inicializamos en cero
    var listPassengers = Set[Passenger]()

    // Creamos dos objetos de tipo estación
    val portalAmericas = new Station(1, "Portal americas", listPassengers, true)
    val calle1 = new Station(2, "Calle 1", listPassengers, true)

    println(portalAmericas.name)
    println(portalAmericas.stationHub)

    val metro1 = new MetroCar(1, 900, portalAmericas, listPassengers)

    var passenger1 = new Passenger(100, "7:00:00 a. m.", portalAmericas, null, calle1, metro1)
    var passenger2 = new Passenger(101, "7:00:00 a. m.", portalAmericas, null, calle1, metro1)

    passenger1.foo

    listPassengers += passenger1
    listPassengers += passenger2

    portalAmericas.amountPassengers = listPassengers

    println("Pasajeros en Portal: "+portalAmericas.amountPassengers.size)

    println(listPassengers.size)

    if (listPassengers.size > 0) {
      for (a <- listPassengers) {
        println(a.id)
        println(a.entranceTime)
        println(a.entranceStation.name)
        println(a.entranceTime)
        println(a.metroCar.id)
        println("****************")
      }
    }
  }
}
