package models

class Passenger(
                 var id: Int = 0,
                 var entranceTime: String = "null",
                 var entranceStation: Station = null,
                 var departureTime: String = "null",
                 var destination: Station = null,
                 var metroCar: MetroCar = null
               ) {

  def foo {
    println("Hola Foo")
  }

}
