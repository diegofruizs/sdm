package models

import java.util.ArrayList

class MetroCar(
                var id: Int = 0,
                var capacity: Int = 0,
                var station: Station = null,
                var passengers: ArrayList[Passenger] = null,
                var schedules: ArrayList[Schedule] = null
              ) {

  def getMetroCars(): ArrayList[MetroCar] = {

    val lista: ArrayList[MetroCar] = new ArrayList[MetroCar]()
    var car1 = new MetroCar(1, 900, new Station(), new Passenger().getPassengers(), new Schedule().getSchedules())
    var car2 = new MetroCar(2, 900, new Station(), new Passenger().getPassengers(), new Schedule().getSchedules())
    var car3 = new MetroCar(3, 900, new Station(), new Passenger().getPassengers(), new Schedule().getSchedules())

    lista.add(car1)
    lista.add(car2)
    lista.add(car3)

    lista
  }

}
