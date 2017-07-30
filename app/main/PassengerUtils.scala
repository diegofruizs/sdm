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
          pass.add(new Passenger(cols(0).toInt,
            cols(2) + " " + formatter.format(today),
            StartUtils.searchStation(cols(1).toString),
            cols(3) + " " + formatter.format(today),
            StartUtils.searchStation(cols(4).toString)))
          //searchMetroCarToGetOn(StartUtils.searchStation(cols(1).toString), StartUtils.searchStation(cols(4).toString), cols(2) + " " + formatter.format(today))
          //println(pass.get(pass.size() - 1))
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

  //def searchMetroCarToGetOn(originStation: Station, destinationStation: Station, entranceTime: String): MetroCar = {
  def searchMetroCarToGetOn(): Unit = {

    val today = Calendar.getInstance().getTime()

    var count = 0

    var cars = MetroCarsUtils.searchCurrentStation()

    for (b <- 0 until pass.size() - 1) {

      for (a <- 0 until cars.size() - 1) {

        //Pregunta por los MetroCars que estan en el instante de tiempo en cada estación del sistema

        if (cars.get(a).currentSchedule != null) {

          //Pregunta si los MetroCars que se encuentran en una estación llevan la ruta que necesita el pasajero

          if (cars.get(a).currentSchedule.departureStation.name.equals(pass.get(b).entranceStation.name)
            && cars.get(a).currentSchedule.destination.name.equals(pass.get(b).destination.name)) {

            //Pregunta si la hora de entrada del pasajero esta antes de la hora de salida del MetroCar
            if (StartUtils.parserStringToTimeWithoutSeconds(pass.get(b).entranceTime).
              before(StartUtils.parserStringToTime(cars.get(a).currentSchedule.departureTime))
              && StartUtils.parserStringToTimeWithoutSeconds(pass.get(b).entranceTime).after(today)) {
              // Asigno al pasajero el MetroCar, en el que se sube.
              pass.get(b).currentMetroCar = cars.get(a)
              // Le asigno la hora en la que se sube al MetroCar
              pass.get(b).entranceTimeToMetroCar = cars.get(a).currentSchedule.departureTime
              println("La hora que llega: " + pass.get(b).entranceTime + " || La hora en que sale el car: " + cars.get(a).currentSchedule.departureTime)
              println("La subo en el bus: " + cars.get(a).id + "|| " + cars.get(a).currentSchedule.departureStation.name + " || " + cars.get(a).currentSchedule.destination.name)
              count = count + 1
            }
          }
        }
      }
    }
    println("Se subieron " + count + " pasajeros")
  }

  def searchMetroCarToGetOff(): Unit = {
    /*En este metodo debería validarse si el MetroCar en el que va el pasajero ya
    se encuentra en la estación destino del pasajaero, y bajarlo del Metro*/
    //TODO
  }
}
