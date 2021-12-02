package lectures.oop

import scala.language.postfixOps
import math.Numeric.Implicits.infixNumericOps
import math.Fractional.Implicits.infixFractionalOps
import math.Integral.Implicits.infixIntegralOps

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
  */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]
}

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h  // returns Nothing
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if(t.isEmpty) s"$h"
    else s"$h ${t.printElements}"

  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, Empty)))
  val listOfStrings: MyList[String] = new Cons("String", new Cons("2", new Cons("Word", Empty)))

  println(listOfIntegers.toString())
  println(listOfStrings.toString())

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(element: Int): MyList[Int] = new Cons(element, new Cons(element + 1, Empty))
  }).toString)
}
