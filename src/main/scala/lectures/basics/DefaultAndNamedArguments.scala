package lectures.basics

object DefaultAndNamedArguments extends App {
  
  def factorial(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else factorial(n - 1, n * acc)
  }
  
  val fact10 = factorial(10)
  
  def savePicture(format: String = "jpg", width: Int = 800, height: Int = 600): Unit = {
    println("saving picture")
  }
  savePicture()
  
}
