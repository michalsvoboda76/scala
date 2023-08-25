
object ContextualAbstractions {

  given desc: Ordering[Int] = Ordering.fromLessThan(_ > _)

  val aList = List(3, 2, 1, 4,7)
  val aSortedList = aList.sorted

  

  trait Combinator[A]{
    def combine(x: A, y: A): A
  }

  def combineAll[A](list: List[A])(using combinator: Combinator[A]): A = 
    list.reduce{combinator.combine}

  given intCombinator: Combinator[Int] = new Combinator[Int] {
    override def combine(x: Int, y: Int): Int = x + y
  }
  val theSum = combineAll(aList)

  def main(args: Array[String]): Unit = {
    println(s"aList=      $aList")
    println(s"aSortedList=$aSortedList")
 
  }

}
