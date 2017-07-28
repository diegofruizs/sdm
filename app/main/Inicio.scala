package main

import java.text.{DateFormat, SimpleDateFormat}
import java.util
import java.util.{ArrayList, Calendar, Date}

import models.{MetroCar, Passenger, Schedule, Station}

object Inicio {

  def main(args: Array[String]) {
    println("Iniciando creación de objetos de SDM")
    val today = Calendar.getInstance().getTime()
    var str1 = "01:35:10"
    var str2 = "02:50:10"
    var parser1 = parserStringToTime(str1)
    var parser2 = parserStringToTime(str2)

    println(parser1)
    println(parser2)

    println(System.getenv().get("sdm"))

    if (parser1.before(today) && parser2.after(today)) {
      println("rango")
    }
  }

  def parserStringToTime(time: String): java.util.Date = {
    val formatter = new SimpleDateFormat("hh:mm:ss")
    val date = formatter.parse(time)
    date
  }


  def constuirMetroCars(): util.ArrayList[MetroCar] = {

    // Creación de los Metro cars
    var car1 = new MetroCar(1, 900, null)
    var car2 = new MetroCar(2, 900, null)
    var car3 = new MetroCar(3, 900, null)
    var car4 = new MetroCar(4, 900, null)
    var car5 = new MetroCar(5, 900, null)
    var car6 = new MetroCar(6, 900, null)
    var car7 = new MetroCar(7, 900, null)
    var car8 = new MetroCar(8, 900, null)
    var car9 = new MetroCar(9, 900, null)
    var car10 = new MetroCar(10, 900, null)
    var car11 = new MetroCar(11, 900, null)
    var car12 = new MetroCar(12, 900, null)
    var car13 = new MetroCar(13, 1800, null)
    var car14 = new MetroCar(14, 1800, null)
    var car15 = new MetroCar(15, 1800, null)
    var car16 = new MetroCar(16, 1800, null)
    var car17 = new MetroCar(17, 1800, null)
    var car18 = new MetroCar(18, 1800, null)
    var car19 = new MetroCar(19, 1800, null)
    var car20 = new MetroCar(20, 1800, null)
    var car21 = new MetroCar(21, 1800, null)
    var car22 = new MetroCar(22, 1800, null)
    var car23 = new MetroCar(23, 1800, null)

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

  def readFiles(): ArrayList[MetroCar] = {


    val path = System.getenv().get("sdm")

    val filesHere = (new java.io.File(path)).listFiles
    var size = filesHere.length

    var schedules: ArrayList[Schedule] = new util.ArrayList[Schedule]()

    def readLines(x: java.io.File) {
      println("Leyendo archivos")
      val listaLineas = scala.io.Source.
        fromFile(x.getPath).getLines()
        .toList
      for (line <- listaLineas) {
        val cols = line.split(";")
        if (!cols(0).equals("train_id") && !cols(1).equals("departure_station") && !cols(2).equals("departure_time") && !cols(3).equals("destination")) {
          schedules.add(new Schedule(1, searchCarsForSchedule(cols(0).toInt), searchStationsForSchedule(cols(1).toString), cols(2), searchStationsForSchedule(cols(3).toString)))
          //println(cols(0) + " " + cols(1) + " " + cols(2) + " " + cols(3))
        }
      }
      println("Size Schedules: " + schedules.size())

    }

    if (filesHere.nonEmpty) {
      for (a <- 0 to filesHere.length - 1) {
        if (filesHere(a).getName.endsWith(".csv") && filesHere(a).getName().equals("schedules.csv")) {
          println("Leyendo los Schedules " + filesHere(a).getName)
          readLines(filesHere(a))
        }
      }
    } else {
      print("No hay files")
    }
    mergeSchedulestoCars(schedules)
  }


  def searchCarsForSchedule(metroId: Int): MetroCar = {
    var cars: ArrayList[MetroCar] = constuirMetroCars()
    var car: MetroCar = new MetroCar()
    for (a <- 0 to cars.size() - 1) {
      if (cars.get(a).id == metroId) {
        car = cars.get(a)
      }
    }
    car
  }

  def searchStationsForSchedule(stationName: String): Station = {
    var stations: ArrayList[Station] = constuirEstaciones()
    var station: Station = new Station()
    for (a <- 0 to stations.size() - 1) {
      if (stations.get(a).name.equals(stationName)) {
        station = stations.get(a)
      }
    }
    station
  }

  def mergeSchedulestoCars(schedules: ArrayList[Schedule]): ArrayList[MetroCar] = {
    var cars: ArrayList[MetroCar] = constuirMetroCars()
    for (a <- 0 until schedules.size() - 1) {
      for (b <- 0 until cars.size() - 1) {
        if (schedules.get(a).metroCar.id == cars.get(b).id) {
          cars.get(b).schedules.add(schedules.get(a))
        }
      }
    }
    cars
  }
}
