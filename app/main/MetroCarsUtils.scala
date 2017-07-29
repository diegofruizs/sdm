package main

import java.text.SimpleDateFormat
import java.util
import java.util.{ArrayList, Calendar}
import models.{MetroCar, Schedule, Station}

object MetroCarsUtils {

    def searchCurrentStation(): ArrayList[MetroCar] = {
    var cars: ArrayList[MetroCar] = new ArrayList[MetroCar]()
    cars = readFiles()
    val today = Calendar.getInstance().getTime()
    for (a <- 0 to cars.size() - 1) {
      for (b <- 1 to cars.get(a).schedules.size() - 1) {
        if (parserStringToTime(cars.get(a).schedules.get(b - 1).departureTime).before(today) &&
          parserStringToTime(cars.get(a).schedules.get(b).departureTime).after(today)) {
          cars.get(a).currentSchedule = cars.get(a).schedules.get(b)
        }
      }
    }
    cars
  }

  def parserStringToTime(time: String): java.util.Date = {
    val formatter = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy")
    val date = formatter.parse(time)
    date
  }


  def readFiles(): ArrayList[MetroCar] = {

    val path = System.getenv().get("sdm")

    val filesHere = (new java.io.File(path)).listFiles

    var size = filesHere.length

    var schedules: ArrayList[Schedule] = new util.ArrayList[Schedule]()

    def readLines(x: java.io.File) {

      val today = Calendar.getInstance().getTime()

      val formatter = new SimpleDateFormat("dd-MM-yyyy")

      val listaLineas = scala.io.Source.
        fromFile(x.getPath).getLines()
        .toList
      for (line <- listaLineas) {
        val cols = line.split(";")
        if (!cols(0).equals("train_id") && !cols(1).equals("departure_station") && !cols(2).equals("departure_time") && !cols(3).equals("destination")) {
          schedules.add(new Schedule(1, searchCarsForSchedule(cols(0).toInt), searchStationsForSchedule(cols(1).toString), cols(2) + " " + formatter.format(today), searchStationsForSchedule(cols(3).toString)))
        }
      }
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
    var cars: ArrayList[MetroCar] = StartUtils.constuirMetroCars()
    var car: MetroCar = new MetroCar()
    for (a <- 0 to cars.size() - 1) {
      if (cars.get(a).id == metroId) {
        car = cars.get(a)
      }
    }
    car
  }

  def searchStationsForSchedule(stationName: String): Station = {
    var stations: ArrayList[Station] = StartUtils.constuirEstaciones()
    var station: Station = new Station()
    for (a <- 0 to stations.size() - 1) {
      if (stations.get(a).name.equals(stationName)) {
        station = stations.get(a)
      }
    }
    station
  }

  def mergeSchedulestoCars(schedules: ArrayList[Schedule]): ArrayList[MetroCar] = {
    var cars: ArrayList[MetroCar] = StartUtils.constuirMetroCars()
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
