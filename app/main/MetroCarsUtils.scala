package main

import java.text.SimpleDateFormat
import java.util
import java.util.{ArrayList, Calendar, Date}

import models.{MetroCar, Passenger, Schedule, Station}

object MetroCarsUtils {

  private var cars: ArrayList[MetroCar] = readSchedulesFile()

  def getSchedules(): ArrayList[MetroCar] = {
    cars
  }

  def searchCurrentStation(): ArrayList[MetroCar] = {
    val today = Calendar.getInstance().getTime()
    for (a <- 0 to cars.size() - 1) {
      cars.get(a).currentSchedule = null
      var hora1 = new Date()
      var hora2 = new Date()
      for (b <- 1 to cars.get(a).schedules.size() - 1) {
        if (cars.get(a).currentSchedule == null) {
          hora1 = parserStringToTime(cars.get(a).schedules.get(b - 1).departureTime)
          hora2 = parserStringToTime(cars.get(a).schedules.get(b).departureTime)
          if (hora1.before(today) && hora2.after(today)) {
            cars.get(a).currentSchedule = cars.get(a).schedules.get(b)
          }
        }
      }
    }
    cars
  }

  def parserStringToTime(time: String): java.util.Date = {
    val formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy")
    val date = formatter.parse(time)
    date
  }

  def readSchedulesFile(): ArrayList[MetroCar] = {

    //val path = System.getenv().get("sdm")
    val path = "/home/farruza/dev/scala-projects/sdm/app/files"
    val filesHere = (new java.io.File(path)).listFiles
    var size = filesHere.length
    var schedules: ArrayList[Schedule] = new util.ArrayList[Schedule]()

    def readLines(x: java.io.File) {
      val today = Calendar.getInstance().getTime()
      val formatter = new SimpleDateFormat("dd-MM-yyyy")
      val listaLineas = scala.io.Source.fromFile(x.getPath).getLines().toList

      var id: Int = 1
      for (line <- listaLineas) {
        val cols = line.split(";")
        if (!cols(0).equals("train_id") && !cols(1).equals("departure_station") && !cols(2).equals("departure_time") && !cols(3).equals("destination")) {
          schedules.add(new Schedule(id, StartUtils.searchMetrocar(cols(0).toInt),
            StartUtils.searchStation(cols(1).toString), cols(2) + " " + formatter.format(today), StartUtils.searchStation(cols(3).toString)))
          id = id + 1
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


  def mergeSchedulestoCars(schedules: ArrayList[Schedule]): ArrayList[MetroCar] = {
    var cars: ArrayList[MetroCar] = StartUtils.getListMetroCars()
    for (a <- 0 until schedules.size() - 1) {
      for (b <- 0 until cars.size() - 1) {
        if (schedules.get(a).metroCar.id == cars.get(b).id) {
          cars.get(b).schedules.add(schedules.get(a))
        }
      }
    }
    cars
  }

  def countPassengersInAMetroCar(): Unit = {
    var passengers: ArrayList[Passenger] = PassengerUtils.getPassengers()
    for (a <- 0 to cars.size() - 1) {
      for (b <- 0 to passengers.size() - 1) {
        //Consulto los pasajeros que tienen asociado un MEtro car, es decir, que ya se subieron algun metrocar
        if (passengers.get(b).metroCar != null) {
          if (cars.get(a).id == passengers.get(b).metroCar.id) {
            //Lo adiciono a la lista de pasajeros de ese MetroCar
            cars.get(a).passengers.add(passengers.get(b))
          }
        }
      }
    }
  }
}
