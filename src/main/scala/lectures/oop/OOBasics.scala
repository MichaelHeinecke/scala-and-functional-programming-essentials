package lectures.oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  person.greet("Daniel")
  person.greet()

  val person2 = new Person()
}

// constructor
// class parameters are NOT fields: name is not a field, age is a field in this example (val)
class Person(name: String, val age: Int = 0) {
  val x = 2 // field
  println(1 + 3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
  // this.name: parameter is available within class body despite not being a field

  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
} // all side effects in class body will be executed
