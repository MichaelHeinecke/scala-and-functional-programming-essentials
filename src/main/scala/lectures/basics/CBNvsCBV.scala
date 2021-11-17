package lectures.basics

object CBNvsCBV extends App {
  
  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }
  
  def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }
  
  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
  // call by name evaluates the parameter lazily on each call
  
  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)
  
  // printFirst(infinite(), 34)
  printFirst(34, infinite()) // y parameter is never evaluated, hence no stack overflow
  
}
