import scala.collection.mutable._
import scala.reflect.io.File

object test {
  val path = "C:\\Users\\comer\\Desktop\\proyectos\\Github\\sdm\\app\\files"
  val filesHere = (new java.io.File(path)).listFiles
  var size = filesHere.length
  def readLines (x: java.io.File){
    println("Leyendo archivo")
    val listaLineas = scala.io.Source.
      fromFile(x.getPath).getLines()
      .toList
    for (line <- listaLineas) {
      val cols = line.split(";")
      println(s"${cols(1)}")

    }

  }

 if(filesHere.nonEmpty){
   for( a <- 0 to filesHere.length-1){
     if(filesHere(a).getName.endsWith(".csv")){
       println(filesHere(a).getName)
       readLines(filesHere(a))
     }
   }
  }else{
   print("No hay files")
 }


}