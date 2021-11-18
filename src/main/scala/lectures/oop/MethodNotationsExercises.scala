package lectures.oop

import scala.language.postfixOps

object MethodNotationsExercises extends App {

  class Person(val name: String, favouriteFilm: String, val age: Int) {
    def likes(film: String): Boolean = film == favouriteFilm
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"${name} (${nickname})", favouriteFilm, age)
    def unary_! : String = s"$name, catch phrase!"
    def unary_+ : Person = new Person(name, favouriteFilm, age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name"
    def apply(value: Int): String = s"$name watched $favouriteFilm $value times"
    def learns(obj: String): String = s"$name learns $obj"
    def learnsScala: String = learns("Scala")
  }
  
  val mary = new Person("Mary", "Inception", 20)
  println((mary + "The Bearded Pirate")())
  println((+mary).age)
  println(mary.learns("some new stuff"))
  println(mary learnsScala)
  println(mary(2))
}

/*
Exercises
1. Overload the + operator
  mary + "the rockstar" => new person "Mary (the rockstar)"
  
2. Add an age to the Person class
Add a unary + operator => new person with the age + 1
+mary => mary with age incremented

3. Add a "learns" method in the Person class => "Mary learns Scala"
Add a learnsScala method, calls learns method with "Scala"
Use it in postfix notation

4. Overlad apply method
mary.apply(2) => "Mary watched Inception 2 times"
 */
