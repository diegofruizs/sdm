package models
import java.util.ArrayList

class Station(
               var id: Int = 0,
               var name: String = null,
               var amountPassengers: ArrayList[Passenger] = null,
               var stationHub: Boolean = false,
               var destination: Station = null,
               var origin: Station = null
             ) {
}

