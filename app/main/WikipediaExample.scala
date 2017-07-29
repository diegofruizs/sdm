import rx.lang.scala.Observable
import java.net.URL


object AsyncObservable2 extends App {
  /**
   * This example shows a custom Observable that does not block
   * when subscribed to as it spawns a separate thread.
   */
  def customObservableNonBlocking(): Observable[String] = {
    Observable(
      /*
     * This 'call' method will be invoked when the Observable is subscribed to.
     *
     * It spawns a thread to do it asynchronously.
     */

      subscriber => {
        // For simplicity this example uses a Thread instead of an ExecutorService/ThreadPool
        new Thread(new Runnable() {
          def run() {

            val lista_de_palabras_a_buscar: List[String] = List("php", "java", "scala", "html", "sql")

            for (palabra <- lista_de_palabras_a_buscar) {
              println("\n----------------------------------------------------------------\n")
              println("\n---------------------PAGE HTML FROM WIKIPEDIA-------------------\n")
              println("\n----------------------------------------------------------------\n")
              var resultado = new java.util.Scanner(new java.net.URL("https://es.wikipedia.org/wiki/" + palabra).openStream()).useDelimiter("\n")
              while (resultado.hasNext()) {
                if (subscriber.isUnsubscribed) {
                  return
                }
                subscriber.onNext(resultado.next())
              }

            }

            // after sending all values we complete the sequence
            if (!subscriber.isUnsubscribed) {
              subscriber.onCompleted()
            }
          }
        }).start()
      })
  }

  // To see output:
  customObservableNonBlocking().subscribe(println(_))
}
