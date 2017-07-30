package models

import java.util.ArrayList

class Passenger(
                 var id: Int = 0,
                 var entranceTime: String = null,
                 var entranceStation: Station = new Station(),
                 var departureTime: String = null,
                 var destination: Station = new Station(),
                 var currentMetroCar: MetroCar = new MetroCar(),
                 var entranceTimeToMetroCar: String = null,
                 var departureTimeToMetroCar: String = null
               ) {

  var metroCar: MetroCar = null

  override def toString(): String = "Passenger: " + id + " - " + entranceTime + " (" + entranceStation.name + ")"
}
