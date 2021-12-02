package lectures.oop

import scala.reflect.ClassTag

object Exceptions extends App {

  val x: String = null
  println(x.length) // will throw exception

  // 1. throwing and catching exceptions
  val aWeirdValue: String = throw new NullPointerException

  // throwable class extend the Throwable class
  // Exceptions and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // does not influence return type of this expression
    // use finally only for side effects
    println("Finally")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  // throw exception

  /*
  Exercise

  1. Crash program with OutOfMemoryError
  2. Crash with StackOverflowError
  3. PocketCalculator
    - add(x, y)
    - subtract(x, y)
    - multiply(x, y)
    - divide(x, y)

    Throw
    - OverflowException if add(x,y) exceeds Int.MAX_VALUE
    - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
    - MathCalculationException for division by 0
  */

  // OOM
//  val array = Array.ofDim[Int](Int.MaxValue)

  // SO
  def infinite: Int = 1 + infinite
//  val noLimit = infinite

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class DivisionByZeroException extends Exception("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new DivisionByZeroException
      else x / y
    }
  }

  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.add(2, 0))
}
