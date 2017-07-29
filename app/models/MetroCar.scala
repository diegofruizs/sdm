package models

import java.util.ArrayList

class MetroCar(
                var id: Int = 0,
                var capacity: Int = 0,
                var currentSchedule: Schedule = new Schedule(),
                var passengers: ArrayList[Passenger] = new ArrayList[Passenger](),
                var schedules: ArrayList[Schedule] =  new ArrayList[Schedule]()
              ) {

  def getMetroCars(): ArrayList[MetroCar] = {

    val lista: ArrayList[MetroCar] = new ArrayList[MetroCar]()
    var car1 = new MetroCar(1, 900, new Schedule(), new Passenger().getPassengers(), new Schedule().getSchedules())
    var car2 = new MetroCar(2, 900, new Schedule(), new Passenger().getPassengers(), new Schedule().getSchedules())
    var car3 = new MetroCar(3, 900, new Schedule(), new Passenger().getPassengers(), new Schedule().getSchedules())

    lista.add(car1)
    lista.add(car2)
    lista.add(car3)

    lista
  }

}
