package lectures.basics

object ValuesVariablesTypes extends App {
  // values
  val x = 42;
  println(x);
  // vals are immutable
  // types are optional, inferred by compiler
  
  val aString: String = "hello"
  val anotherString = "goodbye"
  // semi-colon is optional
  
  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 12345
  val aLong: Long = 1123234235323523558L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14
  
  // variables
  var aVariable: Int = 4
  
  aVariable = 5 // changing variable is called "side effects" in functional programming
  // prefer vals over vars in functional programming
}
