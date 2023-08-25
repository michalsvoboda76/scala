import scala.util.{Failure, Success, Try}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Advanced extends App {

  lazy val block1 = {

    /** lazy evaluation
      */
    lazy val aLazyValue = 2
    lazy val lazyValueWithSideEffect = {
      println("I am so very lazy!")
      43
    }

    val eagerValue = lazyValueWithSideEffect + 1
    // useful in infinite collections
  }

  lazy val block2 = {

    /** "pseudo-collections": Option, Try
      */
    // def methodWhichCanReturnNull(): String = "hello, Scala"
    def methodWhichCanReturnNull(): String = null
    val anOption = Option(methodWhichCanReturnNull()) // Some("hello, Scala")
    // option = "collection" which contains at most one element: Some(value) or None

    println(s"anOption==$anOption")

    val stringProcessing = anOption match {
      case Some(string) => s"I have obtained a valid string: $string"
      case None         => "I obtained nothing"
    }

    def methodWhichCanThrowException(): String = "blabla"
    // def methodWhichCanThrowException(): String = throw new RuntimeException
    val aTry = Try(methodWhichCanThrowException())
    // a try = "collection" with either a value if the code went well, or an exception if the code threw one

    println(s"aTry==$aTry")

    val anotherStringProcessing = aTry match {
      case Success(validValue) => s"I have obtained a valid string: $validValue"
      case Failure(ex)         => s"I have obtained an exception: $ex"
    }
  }

  val aFuture = Future({
    println("Loading ...")
    Thread.sleep(1000)
    throw RuntimeException("blabla exception")
    println("... DONE!")
    67
  })

  aFuture.onComplete {
    case Success(value) => println(s"Got the callback, meaning = $value")
    case Failure(e)     => e.printStackTrace
  }

  Await.ready(aFuture, Duration("2s"))

  println("And I am done too!")
  // Thread.sleep(2000)
}
