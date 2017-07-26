package models

class Schedule(
                var id: Int = 0,
                var metroCar: MetroCar = null,
                var departureStation: Station = null,
                var departureTime: String = null,
                var destination: Station = null
              ) {

}
