package lectures.fp

import scala.util.Random

object Sequences extends App {
  // Seq
  val aSequence = Seq(1, 3, 2, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  // Ranges
  val inclusiveRange: Seq[Int] = 1 to 10
  inclusiveRange.foreach(println)
  val exclusiveRange: Seq[Int] = 1 until 10
  exclusiveRange.foreach(println)

  // List
  val aList = List(1, 2, 3)
  val prepended = 42 :: aList // also +: prepend operator and :+ append operator
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  // Array
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println)

  // Mutation
  numbers(2) = 0 // syntactic sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // Arrays and Seqs
  val numberSeq: Seq[Int] = numbers // implicit conversion
  println(numberSeq)

  // Vector
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // Vectors vs Lists
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating an element in the middle takes long
  println(getWriteTime(numbersList))

  // depth of tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
}
