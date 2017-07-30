package main

import java.text.SimpleDateFormat
import java.util
import java.util.{ArrayList, Calendar}
import models.{MetroCar, Schedule, Station, Passenger}

object PassengerUtils {

  private var pass: ArrayList[Passenger] = readPassengersFile()

  def getPassengers(): ArrayList[Passenger] = {
    pass
  }

  def readPassengersFile(): ArrayList[Passenger] = {

    val path = System.getenv().get("sdm")
    //val path = "/home/farruza/dev/scala-projects/sdm/app/files"
    val filesHere = (new java.io.File(path)).listFiles
    var size = filesHere.length

    pass = new util.ArrayList[Passenger]()

    def readLines(x: java.io.File) {
      val today = Calendar.getInstance().getTime()
      val formatter = new SimpleDateFormat("dd-MM-yyyy")
      val lines = scala.io.Source.fromFile(x.getPath).getLines().toList

      for (line <- lines) {
        val cols = line.split(";")

        if (!cols(0).equals("passenger_id") && !cols(1).equals("entrance_station") && !cols(2).equals("entrance_time") && !cols(3).equals("departure_time") && !cols(4).equals("destination")) {
          pass.add(new Passenger(cols(0).toInt, cols(2) + " " + formatter.format(today), StartUtils.searchStation(cols(1).toString), cols(3) + " " + formatter.format(today), StartUtils.searchStation(cols(4).toString)))
          println(pass.get(pass.size() - 1))
        }
      }
    }

    if (filesHere.nonEmpty) {
      for (a <- 0 to filesHere.length - 1) {
        if (filesHere(a).getName.endsWith(".csv") && filesHere(a).getName().equals("passengers.csv")) {
          println("Leyendo los passengers.csv " + filesHere(a).getName)
          readLines(filesHere(a))
        }
      }
    } else {
      print("No hay files")
    }
    pass
  }
}
