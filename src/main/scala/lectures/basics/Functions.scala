package lectures.basics

object Functions extends App {

  def aFunction(a: String, b: Int): String =
    a + " " + b

  println(aFunction("hello", 5))

  def aParameterlessFunction: Int = 42

  println(aParameterlessFunction)


  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  // return type is optional for non-recursive functions

  println(aRepeatedFunction("hello", 4))
  // when you need loops, use recursion

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  // Exercises
  // 1. Greeting function
  def greetingFunction(name: String, age: Int): Unit =
    println("Hi my name is " + name + " and I am " + age + "years old.")

  greetingFunction("Peter", 5)

  // 2. Calculate factorial
  def calculateFactorial(number: Int): Int = {
    if (number <= 0) number
    else number * calculateFactorial(number - 1)
  }

  println(calculateFactorial(4))

  // 3. Calculate fibonacci number
  def calculateFibonacciNumber(number: Int): Int = {
    if (number <= 2) 1
    else calculateFibonacciNumber(number - 1) + calculateFibonacciNumber(number - 2)
  }

  println(calculateFibonacciNumber(1))
  println(calculateFibonacciNumber(2))
  println(calculateFibonacciNumber(3))
  println(calculateFibonacciNumber(4))
  println(calculateFibonacciNumber(5))
  println(calculateFibonacciNumber(8))

  // 4. Test if number is prime
  def isPrime(number: Int): Boolean = {
    def isPrimeUntil(divisor: Int): Boolean = {
      if (divisor <= 1) true
      else number % divisor != 0 && isPrimeUntil(divisor - 1)
    }

    isPrimeUntil(number / 2)
  }
  println(isPrime(2))
  println(isPrime(3))
  println(isPrime(6))
}
