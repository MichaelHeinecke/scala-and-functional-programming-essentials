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

 4. HOF and curries exercises
 4.1 foreach method A => Unit
     [1, 2, 3].foreach(x => println(x))

 4.2 sort function ((A, A) => Int) => MyList
     [1, 2, 3].sort((x, y) => y - x) => [3, 2, 1]

 4.3 zipWith (list, A, A) => B) => MyList[B]
     [1, 2, 3].zipWith([4, 5, 6], y * x) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]

 4.4 fold(start)(function) => a value
     [1, 2, 3].fold(0)(x + y) = 6
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
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip:(A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B

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
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int): Empty.type = Empty

  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if(!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start


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

  def foreach(f: A => Unit): Unit =
    f(h)
    t.foreach(f)

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  /*
  [1, 2, 3].fold(0)(+) =
      = [2, 3].fold(1)(+) =
      = [3].fold(3) =
      = [].fold(6)(+)
      = 6
  */
  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)

  def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val clonedListOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = Cons(4, Cons(5, Cons(6, Empty)))
  val listOfStrings: MyList[String] = Cons("String", Cons("2", Cons("Word", Empty)))

  println(listOfIntegers.toString())
  println(listOfStrings.toString())

  println(listOfIntegers.map(_ * 2).toString) // element => element * 2

  println(listOfIntegers.filter(_ % 2 == 0).toString) // element => element % 2 == 0

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(element => Cons(element, Cons(element + 1, Empty))).toString)

  // equals is implemented by case class
  println(clonedListOfIntegers == listOfIntegers)

  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x, y) => y - x))
  println(anotherListOfIntegers.zipWith(listOfStrings, _ + "-" + _))
  println(listOfIntegers.fold(0)(_ + _))
}
