object FunctionalProgramming extends App {

  val block = {
    class Person(name: String) {
      def apply(age: Int): Unit = println(s"I have aged $age years")
    }

    val bob = new Person("Bob")
    bob.apply(43)
    bob(44)
    bob {
      println("invoking Bob's apply method via curly brackets")
      45
    }

  }

  val block2 = {
    val formaterFunc = (s: String, x: Int) => s"$s $x"

    println(formaterFunc("test format func no", 1))

    val simpleIncrementer = new Function1[Int, Int] {
      override def apply(v1: Int): Int = v1 + 1
    }

    val aaa = 14
    val bbb = simpleIncrementer(aaa)
    println(s"aaa==$aaa")
    println(s"bbb==$bbb")

    val otherFunc = new Function[Int, Int] {
      override def apply(v1: Int): Int = v1 + 1
    }

    val otherFunc2 = (x: Int) => x + 1

    assert(simpleIncrementer(10) == otherFunc(10))
    assert(simpleIncrementer(10) == otherFunc2(10))
  }

  val block3 = {

    val aMappedList = List(1, 2, 3).map(x => s"$x + 1" /*x + 1*/ )
    println(s"aMappedList == $aMappedList")

    val aFlatList = List(1, 2, 3).flatMap(x => List("x:", x))
    println(s"aFlatList == $aFlatList")

    val aFilteredList = List(1, 2, 3, 4, 5).filter(_ > 2)
    println(s"aFilteredList == $aFilteredList")

    val allPairs = List(1, 2, 3).flatMap(number =>
      List('a', 'b', 'c').map(letter => s"$number$letter")
    )
    println(s"allPairs  == $allPairs")

    val allPairs2 = for {
      number <- List(1, 2, 3)
      // number2 <- List(30,20,10)
      letter <- List('a', 'b', 'c')
    } yield s"$number$letter" // s"$number$number2$letter"
    println(s"allPairs2 == $allPairs2")
  }

  val block4 = {
    val aList = List(1, 2, 3, 4, 5)
    val aPrependedList = 0 :: aList
    println(s"aPrependedList  == $aPrependedList")
    val anExtendedList = 88 +: aList :+ 77
    println(s"anExtendedList  == $anExtendedList")

    val aSeq = Seq('a', 'b', 'c')
    val letter = aSeq(1)
    println(s"on the 1st index of $aSeq is the letter '$letter'")
  }

}
