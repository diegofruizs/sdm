package main

import rx.lang.scala.Observable
import java.util.{ArrayList}
import models.{Passenger}

import java.net.URL
import java.util.Scanner

object LoadPassengerFiles extends App {

  def action(wikipediaArticleNames: String*) = {
    new Thread(new Runnable() {
      def run() {
        for (articleName <- wikipediaArticleNames) {
          val url = "https://en.wikipedia.org/wiki/" + articleName
          val art = new Scanner(new URL(url).openStream()).useDelimiter("\\A").next()
          println(art)
        }
      }
    }).start()
  }

  //override def main(args: Array[String]) {
  //  action("Tiger", "Elephant").subscribe(art => println("--- Article ---\n" + art.substring(0, 125)))
  //}
}
