// based on the example "Sending HTTP Requests in Scala and Akka in 5 minutes"
//  https://blog.rockthejvm.com/a-5-minute-akka-http-client/

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import java.net.URLEncoder
import scala.concurrent.Future
import scala.concurrent.duration._

object SendingHTTPRequestsInScalaAndAkkaIn5minutes {

  implicit val system = ActorSystem() // Akka actors
  implicit val materializer = ActorMaterializer() // Akka Streams
  import system.dispatcher // a "thread pool"

  val sourceCodeToBeHighlighted = """
    class Animal {
    val age: Int = 0
    def eat() = println("I'am eating")
  }

  object Animal {
    val canLiveIdenfinitely = false
  }

  val canLiveIndefinitely = Animal.canLiveIdenfinitely

  val anAnimal = new Animal

  """

  val request = HttpRequest(
    method = HttpMethods.POST,
    uri = "https://markup.su/api/highlighter",
    // uri = "https://jsonplaceholder.typicode.com/posts",
    entity = HttpEntity(
      ContentTypes.`application/x-www-form-urlencoded`,
      s"source=${URLEncoder.encode(sourceCodeToBeHighlighted.trim, "UTF-8")}&language=Scala&theme=Sunburst"
    )

    // method = HttpMethods.GET,
    // uri = "https://jsonplaceholder.typicode.com/posts/1",
    // uri = "http://www.google.com/"
    
  )

  def sendRequest(): Future[String] = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(request)
    val entityFuture: Future[HttpEntity.Strict] =
      responseFuture.flatMap(response => response.entity.toStrict(2.seconds))
    entityFuture.map(entity => entity.data.utf8String)
  }

  def main(args: Array[String]): Unit = {
    println("----------------------------")
    sendRequest().foreach(println)
    println("----------------------------")
  }
}
