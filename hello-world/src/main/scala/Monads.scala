// from article "Monads in Scala"  https://www.baeldung.com/scala/monads
object Monads extends App {

  // 2.1. Wrapping Values Inside a Monad: the Unit Function
  class Lazy[+A](value: => A) {
    private lazy val internal: A = value
    def get: A = internal
  }

  object Lazy {
    def apply[A](value: => A): Lazy[A] = new Lazy(value)
  }

  val lazyInt: Lazy[Int] = Lazy {
    println("The response to everything is 42")
    42
  }

  val x = 33+11
  val lazyX: Lazy[Int] = Lazy {
    println("The X")
    x
  }

  val a = Lazy(55+11)
  println(s"a==${a.get}")
   
  println(s"a==${a.get}")
  
  println(s"lazyX==${lazyX.get}")
  
  println(s"lazyInt==${lazyInt.get}")
}
