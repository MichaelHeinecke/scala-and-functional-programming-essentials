package lectures.oop

object Objects {

  // Scala does not have class-level functionality ("static")

  object Person { // type + its only instance
    // "static"/"class" class-level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level functionality
  }

  // pattern of of object and class in same scope is called "companions"

  // Scala appplications = Scala object with
  // def main(args: Array[String]): Unit
  // object ... extends App -> is translated to java main method to run in JVM
  // public static void main(String[] args
  def main(args: Array[String]): Unit = {
    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = Singleton Instance
    val person1 = Person
    val person2 = Person
    println(person1 == person2)
    val mary = new Person("Mary")
    val john = new Person("John")
    println(mary == john)

    val bobbie = Person(mary, john) // apply method from object Person
  }

}
