package models
import java.util.ArrayList

class Station(
               var id: Int = 0,
               var name: String = "Estación test",
               var amountPassengers: ArrayList[Passenger] = null,
               var stationHub: Boolean = false,
               var destination: Station = null,
               var origin: Station = null
             ) {


  def getStations(): ArrayList[Station] ={

    val lista: ArrayList[Station] = new ArrayList[Station]()
    var portalAmericas = new Station(1,"Portal americas",new Passenger().getPassengers(),true,new Station(),new Station())
    var Calle1 = new Station(2,"Calle 1",new Passenger().getPassengers(),true,new Station(),new Station())
    var Calle72 = new Station(3,"Calle 72",new Passenger().getPassengers(),true,new Station(),new Station())


    lista.add(portalAmericas)
    lista.add(Calle1)
    lista.add(Calle72)

    lista
  }
}

