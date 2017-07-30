package main
import java.text.SimpleDateFormat
import java.util
import java.util.ArrayList

import models.{MetroCar, Station}

object StartUtils {

  def getListMetroCars(): util.ArrayList[MetroCar] = {

    var cars: ArrayList[MetroCar] = new util.ArrayList[MetroCar]()

    // Creación de los Metrocars
    cars.add(new MetroCar(1, 1800, "Portal Americas", "Calle 1"))
    cars.add(new MetroCar(2, 1800, "Calle 72", "Calle 1"))
    cars.add(new MetroCar(3, 1800, "Calle 1", "Portal Americas"))
    cars.add(new MetroCar(4, 1800, "Calle 1", "Calle 72"))
    cars.add(new MetroCar(5, 1800, "Portal Americas", "Calle 72"))
    cars.add(new MetroCar(6, 1800, "Calle 72", "Portal Americas"))
    cars.add(new MetroCar(7, 1800, "Portal Americas", "Calle 1"))
    cars.add(new MetroCar(8, 1800, "Calle 72", "Calle 1"))
    cars.add(new MetroCar(9, 1800, "Calle 1", "Portal Americas"))
    cars.add(new MetroCar(10, 1800, "Calle 1", "Calle 72"))

    cars.add(new MetroCar(11, 1800, "Portal Americas", "Calle 72"))
    cars.add(new MetroCar(12, 1800, "Calle 72", "Portal Americas"))
    cars.add(new MetroCar(13, 900, "Portal Americas", "Calle 1"))
    cars.add(new MetroCar(14, 900, "Calle 72", "Calle 1"))
    cars.add(new MetroCar(15, 900, "Calle 1", "Portal Americas"))
    cars.add(new MetroCar(16, 900, "Calle 1", "Calle 72"))
    cars.add(new MetroCar(17, 900, "Portal Americas", "Calle 72"))
    cars.add(new MetroCar(18, 900, "Calle 72", "Portal Americas"))
    cars.add(new MetroCar(19, 900, "Portal Americas", "Calle 1"))
    cars.add(new MetroCar(20, 900, "Calle 72", "Calle 1"))

    cars.add(new MetroCar(21, 900, "Calle 1", "Portal Americas"))
    cars.add(new MetroCar(22, 900, "Calle 1", "Calle 72"))
    cars.add(new MetroCar(23, 900, "Portal Americas", "Calle 72"))
    cars
  }

  def getListStations(): util.ArrayList[Station] = {

    var stations: ArrayList[Station] = new util.ArrayList[Station]()

    // Creación de estaciones
    stations.add(new Station(1, "Portal Americas", null, true, null, null))
    stations.add(new Station(2, "Calle 42 sur", null, false, null, null))
    stations.add(new Station(3, "Carrera 80", null, false, null, null))
    stations.add(new Station(4, "Kennedy", null, false, null, null))
    stations.add(new Station(5, "Avenida Boyaca", null, false, null, null))
    stations.add(new Station(6, "Carrera 68", null, false, null, null))
    stations.add(new Station(7, "Carrera 50", null, false, null, null))
    stations.add(new Station(8, "NQS", null, false, null, null))
    stations.add(new Station(9, "Narino", null, false, null, null))
    stations.add(new Station(10, "Calle 1", null, true, null, null))
    stations.add(new Station(11, "Calle 10", null, false, null, null))
    stations.add(new Station(12, "Calle 26", null, false, null, null))
    stations.add(new Station(13, "Calle 45", null, false, null, null))
    stations.add(new Station(14, "Calle 63", null, false, null, null))
    stations.add(new Station(15, "Calle 72", null, false, null, null))

    stations
  }

  def searchMetrocar(metroId: Int): MetroCar = {
    var cars: ArrayList[MetroCar] = StartUtils.getListMetroCars()
    var car: MetroCar = new MetroCar()
    for (a <- 0 to cars.size() - 1) {
      if (cars.get(a).id == metroId) {
        car = cars.get(a)
      }
    }
    car
  }

  def searchStation(stationName: String): Station = {
    var stations: ArrayList[Station] = StartUtils.getListStations()
    var station: Station = new Station()
    for (a <- 0 to stations.size() - 1) {
      if (stations.get(a).name.equals(stationName)) {
        station = stations.get(a)
      }
    }
    station
  }

  def parserStringToTime(time: String): java.util.Date = {
    val formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy")
    val date = formatter.parse(time)
    date
  }

  def parserStringToTimeWithoutSeconds(time: String): java.util.Date = {
    val formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy")
    val date = formatter.parse(time)
    date
  }
}
