package models

import java.util.ArrayList

class Schedule(
                var id: Int = 0,
                var metroCar: MetroCar = null,
                var departureStation: Station = null,
                var departureTime: String = null,
                var destination: Station = null
              ) {

  override def toString(): String = "Schedule: " + id + " - " + departureTime + " (" + departureStation.name + " -> " + destination.name + ")"
}
