package models

class MetroCar(
                var id: Int = 0,
                var capacity: Int = 0,
                var station: Station = null,
                var passengers: Set[Passenger] = null
              ) {

}
