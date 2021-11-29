package lectures.oop

object Inheritance extends App {
  
  // Scala has single class inheritance
  class Animal {
    protected val creatureType = "wild"
    def eat = println("nomnomnom")
  }
  
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }
  
  val cat = new Cat
  cat.crunch
  
  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  
  class Adult(name: String, age: Int, idCard: String) extends Person(name)
  /* 
  super-constructors has to be called first, hence parameters must be provided
  if no parameters are provided, the compiler will look for constructor in the super-class
  with the same signature as the one of the sub-class
   */
  
  // overriding
  class Dog(override val creatureType: String) extends Animal {
//    override val creatureType = "domestic"
    override def eat = {
      super.eat
      println("crunch, crunch")
    }
  }
  
  val dog = new Dog("domestic")
  dog.eat
  println(dog.creatureType)
  
  // type substitution
  val unknownAnimal: Animal = new Dog("domestic")
  unknownAnimal.eat
  
  // super
  
  // preventing overriding: 
  // 1. use "final" keyword on member or 
  // 2. use "final" keyword on entire class
  // 3. seal class with "sealed" keyword: extend classes in THIS FILE, prevent extension in other files
  
}
