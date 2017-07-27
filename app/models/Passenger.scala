package models

import java.util.ArrayList

class Passenger(
                 var id: Int = 0,
                 var entranceTime: String = null,
                 var entranceStation: Station = null,
                 var departureTime: String = null,
                 var destination: Station = null,
                 var metroCar: MetroCar = null
               ) {



  def getPassengers(): ArrayList[Passenger] ={

    val lista: ArrayList[Passenger] = new ArrayList[Passenger]()
    var passenger1 = new Passenger(200, "7:00:00 a. m.", new Station(), null, new Station(), new MetroCar())
    var passenger2 = new Passenger(201, "7:10:00 a. m.", new Station(), null, new Station(), new MetroCar())
    var passenger3 = new Passenger(202, "7:20:00 a. m.", new Station(), null, new Station(), new MetroCar())


    lista.add(passenger1)
    lista.add(passenger2)
    lista.add(passenger3)

    lista
  }


}
