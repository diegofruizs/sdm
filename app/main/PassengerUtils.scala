package main

import java.text.SimpleDateFormat
import java.util
import java.util.{ArrayList, Calendar, Date}
import models.{MetroCar, Schedule, Station, Passenger, DensityPassenger}

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
          var sc: (Schedule, Schedule) = searchMetroCarToGetOn_v2(cols(1).toString, cols(2) + " " + formatter.format(today), cols(1).toString)
          var metroCar: MetroCar = null
          var entranceTimeToMetroCar: String = null
          var departureTimeToMetroCar: String = null

          if(sc._1 != null) {
            metroCar = sc._1.metroCar
            entranceTimeToMetroCar = sc._1.departureTime
          }

          if(sc._2 != null) {
            departureTimeToMetroCar = sc._2.departureTime

          }

          pass.add(new Passenger(cols(0).toInt,
            cols(2) + " " + formatter.format(today),
            StartUtils.searchStation(cols(1).toString),
            cols(3) + " " + formatter.format(today),
            StartUtils.searchStation(cols(4).toString),
            metroCar, entranceTimeToMetroCar, departureTimeToMetroCar))
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

  def searchMetroCarToGetOn_v2(entrance_station: String, entrance_time: String, destination_station: String): (Schedule, Schedule)  = {
    var routes: ArrayList[(Int, Int)] = StartUtils.determinateRoute(entrance_station, destination_station)
    //println(routes)
    var sch = MetroCarsUtils.getSchedules()
    var sc_ent: Schedule = null
    var sc_dep: Schedule = null

    // Recorrer los Metrocars
    for (m <- 0 until sch.size()) {
      // Recorrer las rutas posibles
      for (r <- 0 until routes.size()) {
        // Solo tener en cuenta los Metrocars de las rutas posibles
        if (sch.get(m).route == routes.get(r)._1) {
          var flag_ent = false
          var flag_dep = false
          // Recorrer la planificación del Metrocar
          for (s <- 0 until sch.get(m).schedules.size()) {
            var destination_route: String = StartUtils.getRoute(routes.get(r)._1).stations.get(0)
            if (routes.get(r)._2 == 1)
              destination_route = StartUtils.getRoute(routes.get(r)._1).stations.get(StartUtils.getRoute(routes.get(r)._1).stations.size() - 1)

            if ( !flag_ent
              && sch.get(m).schedules.get(s).departureStation.name.equals(entrance_station)
              && StartUtils.parserStringToTimeWithoutSeconds(entrance_time).before(StartUtils.parserStringToTime(sch.get(m).schedules.get(s).departureTime))
              && destination_route.equals(sch.get(m).schedules.get(s).destination.name)) {
              if (sc_ent == null || MetroCarsUtils.parserStringToTime(sc_ent.departureTime).after(MetroCarsUtils.parserStringToTime(sch.get(m).schedules.get(s).departureTime))) {
                sc_ent = sch.get(m).schedules.get(s)
              }
              flag_ent = true
            }

            if ( flag_ent && !flag_dep
              && sch.get(m).schedules.get(s).departureStation.name.equals(destination_station)
              && StartUtils.parserStringToTime(sc_ent.departureTime).before(StartUtils.parserStringToTime(sch.get(m).schedules.get(s).departureTime))
              && destination_route.equals(sch.get(m).schedules.get(s).destination.name)) {
              if (sc_dep == null || MetroCarsUtils.parserStringToTime(sc_dep.departureTime).after(MetroCarsUtils.parserStringToTime(sch.get(m).schedules.get(s).departureTime))) {
                sc_dep = sch.get(m).schedules.get(s)
              }
              flag_dep = true
            }
          }
        }
      }
    }
    (sc_ent, sc_dep)
  }

  def densityPassenger(station: String): ArrayList[DensityPassenger] = {
    var rs: ArrayList[DensityPassenger] = new ArrayList[DensityPassenger]()

    var initial_time: Calendar = Calendar.getInstance()
    initial_time.set(Calendar.MILLISECOND, 0);
    initial_time.set(Calendar.SECOND, 0);
    initial_time.set(Calendar.MINUTE, 0);
    initial_time.set(Calendar.HOUR_OF_DAY, 4);

    var final_time: Calendar = Calendar.getInstance()
    final_time.set(Calendar.MILLISECOND, 0);
    final_time.set(Calendar.SECOND, 59);
    final_time.set(Calendar.MINUTE, 59);
    final_time.set(Calendar.HOUR_OF_DAY, 23);

    var interval_time: Calendar = Calendar.getInstance()
    interval_time.setTime(initial_time.getTime)
    interval_time.add(Calendar.MINUTE, 10);

    val formatter = new SimpleDateFormat("HH:mm")

    while(interval_time.before(final_time)) {
      var range: String = formatter.format(initial_time.getTime) + " -> " + formatter.format(interval_time.getTime)
      rs.add(new DensityPassenger(range, initial_time.getTime, interval_time.getTime, 0, 0))
      initial_time.setTime(interval_time.getTime)
      interval_time.add(Calendar.MINUTE, 10);
    }
    for (a <- 0 until pass.size()) {
      // Hora de Ingreso
      if(pass.get(a).entranceStation.name.equals(station)) {
        var entranceTime: Date = StartUtils.parserStringToTimeWithoutSeconds(pass.get(a).entranceTime)
        var flag: Boolean = false
        for(i <- 0 until rs.size() if !flag) {
          if( entranceTime.compareTo(rs.get(i).initial_time) >= 0 && entranceTime.compareTo(rs.get(i).final_time) < 0 ) {
            rs.get(i).amountPassengersEntering = rs.get(i).amountPassengersEntering + 1
            flag = true
          }
        }
      }

      // Hora de salida
      if(pass.get(a).destination.name.equals(station)) {
        if(pass.get(a).departureTimeToMetroCar != null) {
          var departureTime: Date = StartUtils.parserStringToTime(pass.get(a).departureTimeToMetroCar)
          var flag: Boolean = false
          for(i <- 0 until rs.size() if !flag) {
            if( departureTime.compareTo(rs.get(i).initial_time) >= 0 && departureTime.compareTo(rs.get(i).final_time) < 0 ) {
              rs.get(i).amountPassengersLeaving = rs.get(i).amountPassengersLeaving + 1
              flag = true
            }
          }
        }
      }
    }

    for(x <- 0 until rs.size())
      if( rs.get(x).amountPassengersEntering > 0 || rs.get(x).amountPassengersLeaving > 0 )
        println(rs.get(x))
    rs
  }
}
