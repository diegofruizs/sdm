package models

class Station(
               var id: Int = 0,
               var name: String = null,
               var amountPassengers: Set[Passenger] = null,
               var stationHub: Boolean = false,
               var destination: Station = null,
               var origin: Station = null
             ) {
}

