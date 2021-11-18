package lectures.oop

import scala.language.postfixOps

object MethodNotations extends App {
  
  class Person(val name: String, favouriteFilm: String) {
    def likes(film: String): Boolean = film == favouriteFilm
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, catch phrase!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name"
  }
  
  val marie = new Person("Mary", "Inception")
  println(marie.likes("Inception"))
  println(marie likes "Inception") // equivalent; called infix notation or operator notation (syntactic sugar)
  // methods with a single parameter can be called this way
  
  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(marie + tom)
  println(marie.+(tom))
  
  println(1 + 2)
  println(1.+(2))
  
  // all operators are methods
  
  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix works only with + - ~ !
  
  println(!marie)
  
  // postfix notation
  println(marie isAlive) // only available to methods without parameters
  
  // apply
  println(marie.apply())
  println(marie()) // equivalent
}
