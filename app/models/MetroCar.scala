package models

import java.util.ArrayList

class MetroCar(
                var id: Int = 0,
                var capacity: Int = 0,
                var departure_station: String = null,
                var destination_station: String = null,
                var currentSchedule: Schedule = new Schedule(),
                var passengers: ArrayList[Passenger] = new ArrayList[Passenger](),
                var schedules: ArrayList[Schedule] =  new ArrayList[Schedule]()
              ) {

}
