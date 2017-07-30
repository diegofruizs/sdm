package main

import java.text.SimpleDateFormat
import java.util
import java.util.{ArrayList, Calendar}
import models.{MetroCar, Schedule, Station, Passenger}

object PassengerUtils {

  private var pass: ArrayList[Passenger] = new ArrayList[Passenger]()

  def getPassengers(): ArrayList[Passenger] = {
    pass
  }

  def readPassengersFile(): ArrayList[Passenger] = {
    //val path = System.getenv().get("sdm")
    val path = "/home/farruza/dev/scala-projects/sdm/app/files"
    val filesHere = (new java.io.File(path)).listFiles
    var size = filesHere.length

    pass = new ArrayList[Passenger]()

    def readLines(x: java.io.File) {
      val today = Calendar.getInstance().getTime()
      val formatter = new SimpleDateFormat("dd-MM-yyyy")
      val lines = scala.io.Source.fromFile(x.getPath).getLines().toList

      for (line <- lines) {
        val cols = line.split(";")

        if (!cols(0).equals("passenger_id") && !cols(1).equals("entrance_station") && !cols(2).equals("entrance_time") && !cols(3).equals("departure_time") && !cols(4).equals("destination")) {
          var sc: Schedule = searchMetroCarToGetOn_v2(cols(1).toString, cols(2) + " " + formatter.format(today), cols(1).toString)
          var metroCar: MetroCar = null
          var departureTime: String = null

          if(sc != null) {
            metroCar = sc.metroCar
            departureTime = sc.departureTime
          }

          pass.add(new Passenger(cols(0).toInt,
            cols(2) + " " + formatter.format(today),
            StartUtils.searchStation(cols(1).toString),
            cols(3) + " " + formatter.format(today),
            StartUtils.searchStation(cols(4).toString),
            metroCar, departureTime))
          println(pass.get(pass.size()-1))
        }
      }
    }

    if (filesHere.nonEmpty) {
      for (a <- 0 to filesHere.length - 1) {
        if (filesHere(a).getName.endsWith(".csv") && filesHere(a).getName().equals("passengers.csv")) {
          println("Leyendo los Passengers " + filesHere(a).getName)
          readLines(filesHere(a))
        }
      }
    } else {
      print("No hay files")
    }
    pass
  }

  def searchMetroCarToGetOn_v2(entrance_station: String, entrance_time: String, destination_station: String): Schedule = {
    var routes: ArrayList[(Int, Int)] = StartUtils.determinateRoute(entrance_station, destination_station)
    //println(routes)
    var sch = MetroCarsUtils.getSchedules()
    var sc: Schedule = null

    // Recorrer los Metrocars
    for (m <- 0 until sch.size()) {
      // Recorrer las rutas posibles
      for (r <- 0 until routes.size()) {
        // Solo tener en cuenta los Metrocars de las rutas posibles
        if (sch.get(m).route == routes.get(r)._1) {
          var flag = false
          // Recorrer la planificación del Metrocar
          for (s <- 0 until sch.get(m).schedules.size()) {
            var destination_route: String = StartUtils.getRoute(routes.get(r)._1).stations.get(0)
            if (routes.get(r)._2 == 1)
              destination_route = StartUtils.getRoute(routes.get(r)._1).stations.get(StartUtils.getRoute(routes.get(r)._1).stations.size() -1)

            if (sch.get(m).schedules.get(s).departureStation.name.equals(entrance_station)
              && StartUtils.parserStringToTimeWithoutSeconds(entrance_time).before(StartUtils.parserStringToTime(sch.get(m).schedules.get(s).departureTime))
              && !flag
              && destination_route.equals(sch.get(m).schedules.get(s).destination.name)) {
              if (sc == null || MetroCarsUtils.parserStringToTime(sc.departureTime).after(MetroCarsUtils.parserStringToTime(sch.get(m).schedules.get(s).departureTime))) {
                sc = sch.get(m).schedules.get(s)
              }
              flag = true
            }
          }
        }
      }
    }
    sc
  }
}
