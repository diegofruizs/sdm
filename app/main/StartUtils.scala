package main
import java.util
import java.util.ArrayList

import models.{MetroCar, Station}

object StartUtils {

  def constuirMetroCars(): util.ArrayList[MetroCar] = {

    // Creación de los Metro cars
    var car1 = new MetroCar(1, 900)
    var car2 = new MetroCar(2, 900)
    var car3 = new MetroCar(3, 900)
    var car4 = new MetroCar(4, 900)
    var car5 = new MetroCar(5, 900)
    var car6 = new MetroCar(6, 900)
    var car7 = new MetroCar(7, 900)
    var car8 = new MetroCar(8, 900)
    var car9 = new MetroCar(9, 900)
    var car10 = new MetroCar(10, 900)
    var car11 = new MetroCar(11, 900)
    var car12 = new MetroCar(12, 900)
    var car13 = new MetroCar(13, 1800)
    var car14 = new MetroCar(14, 1800)
    var car15 = new MetroCar(15, 1800)
    var car16 = new MetroCar(16, 1800)
    var car17 = new MetroCar(17, 1800)
    var car18 = new MetroCar(18, 1800)
    var car19 = new MetroCar(19, 1800)
    var car20 = new MetroCar(20, 1800)
    var car21 = new MetroCar(21, 1800)
    var car22 = new MetroCar(22, 1800)
    var car23 = new MetroCar(23, 1800)

    var cars: ArrayList[MetroCar] = new util.ArrayList[MetroCar]()
    cars.add(car1)
    cars.add(car2)
    cars.add(car3)
    cars.add(car4)
    cars.add(car5)
    cars.add(car6)
    cars.add(car7)
    cars.add(car8)
    cars.add(car9)
    cars.add(car10)
    cars.add(car11)
    cars.add(car12)
    cars.add(car13)
    cars.add(car14)
    cars.add(car15)
    cars.add(car16)
    cars.add(car17)
    cars.add(car18)
    cars.add(car19)
    cars.add(car20)
    cars.add(car21)
    cars.add(car22)
    cars.add(car23)

    cars
  }

  def constuirEstaciones(): util.ArrayList[Station] = {
    // Creación de estaciones
    var portalAmericas = new Station(1, "Portal Americas", null, true, null, null)
    var calle42sur = new Station(2, "Calle 42 sur", null, false, null, null)
    var carrera80 = new Station(3, "Carrera 80", null, false, null, null)
    var kennedy = new Station(4, "Kennedy", null, false, null, null)
    var avenidaBoyaca = new Station(5, "Avenida Boyaca", null, false, null, null)
    var carrera68 = new Station(6, "Carrera 68", null, false, null, null)
    var carrera50 = new Station(7, "Carrera 50", null, false, null, null)
    var nqs = new Station(8, "NQS", null, false, null, null)
    var narino = new Station(9, "Narino", null, false, null, null)
    var calle1 = new Station(10, "Calle 1", null, true, null, null)
    var calle10 = new Station(11, "Calle 10", null, false, null, null)
    var calle26 = new Station(12, "Calle 26", null, false, null, null)
    var calle45 = new Station(13, "Calle 45", null, false, null, null)
    var calle63 = new Station(14, "Calle 63", null, false, null, null)
    var calle72 = new Station(15, "Calle 72", null, false, null, null)

    var estaciones: ArrayList[Station] = new util.ArrayList[Station]()
    estaciones.add(portalAmericas)
    estaciones.add(calle42sur)
    estaciones.add(carrera80)
    estaciones.add(kennedy)
    estaciones.add(avenidaBoyaca)
    estaciones.add(carrera68)
    estaciones.add(carrera50)
    estaciones.add(nqs)
    estaciones.add(narino)
    estaciones.add(calle1)
    estaciones.add(calle10)
    estaciones.add(calle26)
    estaciones.add(calle45)
    estaciones.add(calle63)
    estaciones.add(calle72)

    estaciones
  }

}