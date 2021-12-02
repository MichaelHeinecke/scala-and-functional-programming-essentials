package lectures.oop

import playground.Playground.{Cinderella, SnowWhite => Witch} // names can be aliased to avoid collisions
// import playground.Playground._ // imports all names

object Packaging extends App {

  // package members are accessible by their name
  val writer = new Writer("Johnny", "Cash", 1985)

  // import package
  val princess = new Cinderella

  // package object
  sayHello
  println(SOME_CONSTANT)

  // imports
  val snowWhite = new Witch

}
