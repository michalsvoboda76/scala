@main def hello: Unit =
  println("Hello world!")
  println(msg)

def msg = "I was compiled by Scala 3. :)"

def factorial(n: Int): Int =
  if (n <= 1) 1
  else n * factorial(n - 1)
