package com.rockthejvm

object PatternMatching extends App {

  trait Named{
    val Name: String
  }

  trait Born{
    val Age: Int
  }

  class Creature(val isWalking: Boolean)

  case class Person2(val Name: String, val Age: Int) extends Creature(true) with Named with Born{  }
  case class Stone(val Name: String) extends Creature(false) with Named{}

  val bob = Person2("Bob", 14)

  val sutr = Stone("Sutr")

  println(s"This is ${bob.Name} and his age is ${bob.Age} and isWalking==${bob.isWalking}" )
  println(s"This is ${sutr.Name} and isWalking==${sutr.isWalking}" )

  val creat = sutr
  val message = creat match {
    case n: Named => s"The creature has a name ${n.Name}"
    case a: Born => s"The creature has an age ${a.Age}"
  }

  println(s"This is ${creat} and $message" )

  
}
