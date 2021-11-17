package lectures.basics

object Expressions extends App {
  val x = 1 + 2 // right side is called expression
  println(x)
  
  println(2 + 3 * 4)
  
  // Instructions (do something) vs Expressions (value)
  // IF expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3
  println(aConditionedValue)
  
  // looping is discouragd as it is imperative programming
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }
  
  // Everything in Scala is an Expression
  var aVariable = 2
  val aWeirdValue = (aVariable = 3) // Unit is void in other languages
  println(aWeirdValue)
  
  // side effects: println(), whiles, reassigning
  
  // code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    
    if (z > 2) "hello" else "goodbye"
  } // value of code block is the value of its last expression
}
