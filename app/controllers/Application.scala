package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import models.{Passenger, Station, DensityPassenger}
import java.util.{ArrayList}
import main.{PassengerUtils, StartUtils}

class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getName(station_id: Int) = Action {

    case class DensityPassengerJson(range: String, amountPassengersEntering: Int, amountPassengersLeaving: Int)
    case class Content(station: String, data: Seq[DensityPassengerJson])

    implicit val densityPassengerWrites = new Writes[DensityPassengerJson] {
      def writes(dto: DensityPassengerJson) = Json.obj(
        "range" -> dto.range,
        "amount_entering" -> dto.amountPassengersEntering,
        "amount_leaving" -> dto.amountPassengersLeaving
      )
    }

    implicit val contentWrites = new Writes[Content] {
      def writes(dto: Content) = Json.obj(
        "station" -> dto.station,
        "data" -> dto.data
      )
    }
    var rs: ArrayList[DensityPassenger] = PassengerUtils.densityPassenger(station_id)
    var data = Seq(DensityPassengerJson(rs.get(0).range, rs.get(0).amountPassengersEntering, rs.get(0).amountPassengersLeaving))
    for(index <- 1 until rs.size) {
      data = (data):+DensityPassengerJson(rs.get(index).range, rs.get(index).amountPassengersEntering, rs.get(index).amountPassengersLeaving)
    }

    var station_ent: Station = StartUtils.searchStationById(station_id)
    var station = ""
    if(station_ent != null)
      station = station_ent.name

    Ok(Json.toJson(Content(station, data)))
  }

}
