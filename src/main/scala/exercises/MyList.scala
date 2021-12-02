package exercises

abstract class MyList[+A] {
 /*
 1. Implement singly-linked list
 head: first element of the list
 tail: remainder of the list
 isEmpty
 add(int): new list with this element added
 toString: a string representation of the list

 2. Extended MyList
 2.1 Generic trait MyPredicate[-T] with a method test(T) => Boolean
 2.2 Generic trait MyTransformer[-A, B] with a method transform(A) => B
 2.3 MyList:
  - map(transformer) => MyList
  - filter(predicate) => MyList
  - flatMap(transformer from A to MyList[B]) => MyList[B]

 [1, 2, 3].map(n * 2) = [2, 4, 6]
 [1, 2, 3, 4].filter(n % 2) = [2, 4]
 [1, 2, 3].flatMap(n => [n, n + 1] => [1, 2, 2, 3, 3, 4]

 3. Use case classes and case objects where appropriate
  */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  // higher-order functions
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h  // returns Nothing
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = Cons(element, this)

  def printElements: String =
    if(t.isEmpty) s"$h"
    else s"$h ${t.printElements}"

  def map[B](transformer: A => B): MyList[B] =
    Cons(transformer(h), t.map(transformer))

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val clonedListOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = Cons(4, Cons(5, Cons(6, Empty)))
  val listOfStrings: MyList[String] = Cons("String", Cons("2", Cons("Word", Empty)))

  println(listOfIntegers.toString())
  println(listOfStrings.toString())

  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }).toString)

  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(element: Int): Boolean = element % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(element: Int): MyList[Int] = Cons(element, Cons(element + 1, Empty))
  }).toString)

  // equals is implemented by case class
  println(clonedListOfIntegers == listOfIntegers)
}
