package models

import java.util.ArrayList

class Station(
               var id: Int = 0,
               var name: String = "Estación test",
               var amountPassengers: ArrayList[Passenger] = new ArrayList[Passenger](),
               var stationHub: Boolean = false,
               var destination: Station = null,
               var origin: Station = null
             ) {

}
