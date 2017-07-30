package models

import java.util.ArrayList

class MetroCar(
                var id: Int = 0,
                var capacity: Int = 0,
                var departure_station: String = null,
                var destination_station: String = null,
                var route: Int,
                var amountPassengers: Int = 0
              ) {

  var currentSchedule: Schedule = new Schedule()
  var schedules: ArrayList[Schedule] =  new ArrayList[Schedule]()

  override def toString(): String = "MetroCar: " + id + ". " + departure_station + " -> " + destination_station + " (" + capacity + ")"
}
