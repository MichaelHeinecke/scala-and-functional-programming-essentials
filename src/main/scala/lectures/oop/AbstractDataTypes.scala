package lectures.oop

object AbstractDataTypes extends App {
  
  // abstract class
  // cannot be instantiated
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }
  
  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("Crunch crunch")
  }
  
  // traits
  trait Carnivore {
    def eat(anmial: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }
  
  trait ColdBlooded
  
  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I eat ${animal.creatureType}")
  }
  
  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)
  
  // traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - multiple traits may be inherited by the same class
  // 3 - traits are behaviour, classes are entities
}
