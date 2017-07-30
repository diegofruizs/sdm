package main
import java.text.SimpleDateFormat
import java.util
import java.util.ArrayList

import models.{MetroCar, Station, Route}

object StartUtils {

  private val routes: ArrayList[Route] = new ArrayList[Route]()

  // Creación de Rutas
  val route1: ArrayList[String] = new ArrayList[String]()
  route1.add("Portal Americas")
  route1.add("Calle 42 sur")
  route1.add("Carrera 80")
  route1.add("Kennedy")
  route1.add("Avenida Boyaca")
  route1.add("Carrera 68")
  route1.add("Carrera 50")
  route1.add("NQS")
  route1.add("Narino")
  route1.add("Calle 1")
  routes.add(new Route(route1))

  val route2: ArrayList[String] = new ArrayList[String]()
  route2.add("Calle 1")
  route2.add("Narino")
  route2.add("NQS")
  route2.add("Carrera 50")
  route2.add("Carrera 68")
  route2.add("Avenida Boyaca")
  route2.add("Kennedy")
  route2.add("Carrera 80")
  route2.add("Calle 42 sur")
  route2.add("Portal Americas")
  routes.add(new Route(route2))

  val route3: ArrayList[String] = new ArrayList[String]()
  route3.add("Calle 72")
  route3.add("Calle 63")
  route3.add("Calle 45")
  route3.add("Calle 26")
  route3.add("Calle 10")
  route3.add("Calle 1")
  routes.add(new Route(route3))

  val route4: ArrayList[String] = new ArrayList[String]()
  route4.add("Calle 1")
  route4.add("Calle 10")
  route4.add("Calle 26")
  route4.add("Calle 45")
  route4.add("Calle 63")
  route4.add("Calle 72")
  routes.add(new Route(route4))

  val route5: ArrayList[String] = new ArrayList[String]()
  route5.add("Portal Americas")
  route5.add("Calle 42 sur")
  route5.add("Carrera 80")
  route5.add("Kennedy")
  route5.add("Avenida Boyaca")
  route5.add("Carrera 68")
  route5.add("Carrera 50")
  route5.add("NQS")
  route5.add("Narino")
  route5.add("Calle 1")
  route5.add("Calle 10")
  route5.add("Calle 26")
  route5.add("Calle 45")
  route5.add("Calle 63")
  route5.add("Calle 72")
  routes.add(new Route(route5))

  val route6: ArrayList[String] = new ArrayList[String]()
  route6.add("Calle 72")
  route6.add("Calle 63")
  route6.add("Calle 45")
  route6.add("Calle 26")
  route6.add("Calle 10")
  route6.add("Calle 1")
  route6.add("Narino")
  route6.add("NQS")
  route6.add("Carrera 50")
  route6.add("Carrera 68")
  route6.add("Avenida Boyaca")
  route6.add("Kennedy")
  route6.add("Carrera 80")
  route6.add("Calle 42 sur")
  route6.add("Portal Americas")
  routes.add(new Route(route6))

  def getRoutes(): ArrayList[Route] = routes

  def getRoute(index: Int): Route = routes.get(index)

  private val cars: ArrayList[MetroCar] = new ArrayList[MetroCar]()

  // Creación de los Metrocars
  cars.add(new MetroCar(1, 1800, "Portal Americas", "Calle 1", 0))
  cars.add(new MetroCar(2, 1800, "Calle 72", "Calle 1", 2))
  cars.add(new MetroCar(3, 1800, "Calle 1", "Portal Americas", 1))
  cars.add(new MetroCar(4, 1800, "Calle 1", "Calle 72", 3))
  cars.add(new MetroCar(5, 1800, "Portal Americas", "Calle 72", 4))
  cars.add(new MetroCar(6, 1800, "Calle 72", "Portal Americas", 5))
  cars.add(new MetroCar(7, 1800, "Portal Americas", "Calle 1", 0))
  cars.add(new MetroCar(8, 1800, "Calle 72", "Calle 1", 2))
  cars.add(new MetroCar(9, 1800, "Calle 1", "Portal Americas", 1))
  cars.add(new MetroCar(10, 1800, "Calle 1", "Calle 72", 3))

  cars.add(new MetroCar(11, 1800, "Portal Americas", "Calle 72", 4))
  cars.add(new MetroCar(12, 1800, "Calle 72", "Portal Americas", 5))
  cars.add(new MetroCar(13, 900, "Portal Americas", "Calle 1", 0))
  cars.add(new MetroCar(14, 900, "Calle 72", "Calle 1", 2))
  cars.add(new MetroCar(15, 900, "Calle 1", "Portal Americas", 1))
  cars.add(new MetroCar(16, 900, "Calle 1", "Calle 72", 3))
  cars.add(new MetroCar(17, 900, "Portal Americas", "Calle 72", 4))
  cars.add(new MetroCar(18, 900, "Calle 72", "Portal Americas", 5))
  cars.add(new MetroCar(19, 900, "Portal Americas", "Calle 1", 0))
  cars.add(new MetroCar(20, 900, "Calle 72", "Calle 1", 2))

  cars.add(new MetroCar(21, 900, "Calle 1", "Portal Americas", 1))
  cars.add(new MetroCar(22, 900, "Calle 1", "Calle 72", 3))
  cars.add(new MetroCar(23, 900, "Portal Americas", "Calle 72", 4))

  def getListMetroCars(): ArrayList[MetroCar] = cars

  private val stations: ArrayList[Station] = new ArrayList[Station]()

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

  def getListStations(): ArrayList[Station] = stations

  def searchMetrocar(metroId: Int): MetroCar = {
    var car: MetroCar = null
    for (a <- 0 to cars.size() - 1) {
      if (cars.get(a).id == metroId) {
        car = cars.get(a)
      }
    }
    car
  }

  def searchStation(stationName: String): Station = {
    var station: Station = null
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

  def determinateRoute(entraceStation: String, destination: String): ArrayList[(Int, Int)] = {
    var rs = new ArrayList[(Int, Int)]()

    for (r <- 0 until routes.size()) {
      var pos_entrace: Int = -1
      var pos_destination: Int = -1
      for (s <- 0 until routes.get(r).stations.size()) {

        if(routes.get(r).stations.get(s).equals(entraceStation)) {
          pos_entrace = s
        }

        if(routes.get(r).stations.get(s).equals(destination)) {
          pos_destination = s
        }
      }
      if(pos_entrace != -1 && pos_destination != -1) {
        if(pos_entrace > pos_destination)
          rs.add((r,-1))
        else
          rs.add((r,1))
      }
    }
    rs
  }
}
