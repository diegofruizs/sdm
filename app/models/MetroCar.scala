package models

import java.util.ArrayList

class MetroCar(
                var id: Int = 0,
                var capacity: Int = 0,
                var departure_station: String = null,
                var destination_station: String = null,
                var route: Int
              ) {

  var currentSchedule: Schedule = new Schedule()
  var passengers: ArrayList[Passenger] = new ArrayList[Passenger]()
  var schedules: ArrayList[Schedule] =  new ArrayList[Schedule]()

  override def toString(): String = "MetroCar: " + id + ". " + departure_station + " -> " + destination_station + " (" + capacity + ")"
}
