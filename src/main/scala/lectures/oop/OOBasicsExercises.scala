package lectures.oop

import java.time.Year

object OOBasicsExercises extends App {
  val author1 = new Writer("Daniel", "Jones", 1980)
  val author2 = new Writer("Jack", "Lumber", 1981)
  val fullName = author1.getFullName
  println(fullName)
  
  val novel1 = new Novel("A very good novel", 1991, author1)
  println(novel1.isWrittenBy(author2))
  println(novel1.getAuthorAge)
  val novel2 = novel1.copy(1992)
  println(novel1 == novel2)
  
  val counter = new Counter
  counter.increment.print
  counter.increment.increment.increment.print
  counter.decrement(5).print
}

/*
 Exercise
 Implement Novel and Writer classes
 
 Writer: first name, surname, year of birth
 - method fullname
 
 Novel: name, year of release, author
 - authorAge
 - isWrittenBy(author)
 - copy(new year of release) = new instance of Novel
  */
class Writer(firstName: String, surname: String, val yearOfBirth: Int) {
  def getFullName: String = firstName + " " + surname
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def getAuthorAge: Int = Year.now.getValue - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = this.author == author
  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

/*
Exercise
Counter class
- receives and int value
- method current count
- method to increment/decrement => new Counter
- overload inc/dec to receive an amount to increment/decrement
 */

class Counter(val count: Int = 0) {
  def increment: Counter = {
    println("incrementing")
    new Counter(count + 1)
  } // immutability: return new instance instead of changing stead of existing object
  
  def increment(incrementValue: Int): Counter = {
    if (incrementValue <= 0) this
    else increment.increment(incrementValue - 1)
  }
  
  def decrement: Counter = {
    println("decrementing")
    new Counter(count - 1)
  }
  
  def decrement(decrementValue: Int): Counter = {
    if (decrementValue <= 0) this
    else decrement.decrement(decrementValue - 1)
  }
  
  def print = println(count)
}