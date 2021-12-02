package lectures.fp

object WhatsAFunction {
  // Dream: use functions as first class elements
  // problem: oop orientation of JVM

  val doubler: MyFunction[Int, Int] = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter: String => Int = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R
  // All Scala functions are objects

  /*
  1. Function which concatenates two strings
  2. transform the MyPredicate and MyTransformer into function types
  3. define a function which takes an int and returns another function which takes an int and returns an int
  */

  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = s"$v1 $v2"
  }

  println(concatenator("Hello", "Scala"))


  // Function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3: Int => Int = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
