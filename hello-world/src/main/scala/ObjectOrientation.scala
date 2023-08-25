object ObjectOrientation extends App {
  class Animal {
    val age: Int = 0
    def eat() = println("I'am eating")
  }

  object Animal {
    val canLiveIdenfinitely = false
  }

  val canLiveIndefinitely = Animal.canLiveIdenfinitely

  val anAnimal = new Animal

  class Dog(val name: String) extends Animal {}
  val aDog = new Dog("Lassie")

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Phylosopher {
    def ?!(thought: String): Unit
  }

  class Crocidile extends Animal with Carnivore with Phylosopher {
    override def eat(animal: Animal): Unit = println("I am eating you, animal!")

    override def ?!(thought: String): Unit = println(
      s"I was thinking: $thought"
    )
  }

  val aCroc = new Crocidile
  aCroc.eat(aDog)
  aCroc eat aDog
  aCroc.?!("blabla")
  aCroc ?! "blabla"

  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = "I am dinosaur"
  }

  case class Person(name: String, age: Int)

  val bob = Person("Bob", 45)
  val marry = new Person("Marry", 42)

  try {
    val x: String = null
    //val y = 5/0
    x.length
  } catch {
    case e: NullPointerException => println(s"ERR: NullPointerException faulty state: $e")
    case e2: Exception => println(s"ERR: generic faulty state: $e2")
    
  }

  println("running ObjectOrientation object")
  println(s"a dog name is ${aDog.name}")
}
