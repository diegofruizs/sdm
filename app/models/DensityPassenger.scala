package models

import java.util.ArrayList
import java.util.Date

class DensityPassenger(
               var range: String = null,
               var initial_time: Date = null,
               var final_time: Date = null,
               var amountPassengersEntering: Int = 0,
               var amountPassengersLeaving: Int = 0
             ) {

  override def toString(): String = "DensityPassenger: " + range + ": " + amountPassengersEntering + " -> " + amountPassengersLeaving
}
