package lectures.basics

import scala.annotation.tailrec

object Recursion extends App {

  def calculateFactorial(number: Int): Int = {
    if (number <= 0) number
    else number * calculateFactorial(number - 1)
  }

  def anotherFactorialImplementation(number: Int): BigInt = {
    @tailrec // annotation to tell compiler this is supposed to be tail recursion
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // tail recursion = use recursive call as the last expression on code path
    }

    factorialHelper(number, 1)
  }

  // println(anotherFactorialImplementation(5000))
  
  // when you need loops, use _tail_ recursion
  
  // Exercises - tail recursion
  // 1. Concatenate a string n times
  def concatenate(string: String, n: Int): String = {
    @tailrec
    def concatenateHelper(n: Int, accumulator: String): String = {
      if (n <= 0) accumulator
      else concatenateHelper(n - 1, accumulator + string)
    }
    
    concatenateHelper(n, "")
  }
  
  println(concatenate("hello", 3))
  
  // 2. IsPrime function
  def isPrime(number: Int): Boolean = {
    @tailrec
    def isPrimeUntil(divisor: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (divisor <= 1) true
      else isPrimeUntil(divisor - 1, number % divisor != 0)
    }

    isPrimeUntil(number / 2, true)
  }
  
  println(isPrime(1))
  println(isPrime(2))
  println(isPrime(5))
  println(isPrime(37 * 17))
  
  // 3. Fibonacci function
  def fibonacci(number: Int): Int = {
    @tailrec
    def fibonacciHelper(i: Int, fibonacciMinusOne: Int, fibonacciMinusTwo: Int): Int = {
      if (i >= number) fibonacciMinusOne
      else fibonacciHelper(i + 1, fibonacciMinusOne + fibonacciMinusTwo, fibonacciMinusOne)
    }
    
    if (number <= 2) 1
    else fibonacciHelper(2, 1, 1)
  }
  
  println(fibonacci(500))
}
