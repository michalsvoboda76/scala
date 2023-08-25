// article at https://blog.rockthejvm.com/3-tricks-for-CBN/
object FunTricksWithCallByName extends App {

  // Trick 1 - reevaluation

  def byValueFunction(x: Long): Unit = {
    println(x)
    println(x)
  }

  def byNameFunction(x: => Long): Unit = {
    println(x)
    println(x)
  }

  byValueFunction(System.nanoTime())
  byNameFunction(System.nanoTime())

  // Trick 2 - manageable infinityPermalink

  abstract class LazyList[+T] {
    def head: T
    def tail: LazyList[T]
  }

  case object Empty extends LazyList[Nothing] {
    override def head = throw new NoSuchElementException
    override def tail = throw new NoSuchElementException
  }

  class NonEmpty[+T](h: => T, t: => LazyList[T]) extends LazyList[T] {
    override lazy val head = h
    override lazy val tail = t

  }
}
