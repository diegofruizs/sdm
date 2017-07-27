package models

import java.util.ArrayList

class Schedule(
                var id: Int = 0,
                var metroCar: MetroCar = null,
                var departureStation: Station = null,
                var departureTime: String = null,
                var destination: Station = null
              ) {

  def getSchedules(): ArrayList[Schedule] ={

    val lista: ArrayList[Schedule] = new ArrayList[Schedule]()
    var schedule1 = new Schedule(1,new MetroCar(),new Station(),"07:00:00 am", new Station())
    var schedule2 = new Schedule(2,new MetroCar(),new Station(),"07:10:00 am", new Station())
    var schedule3 = new Schedule(3,new MetroCar(),new Station(),"07:20:00 am", new Station())

    lista.add(schedule1)
    lista.add(schedule2)
    lista.add(schedule3)

    lista

  }

}
