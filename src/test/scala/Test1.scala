
import org.junit.Test
import org.junit.Assert._
import playground.Playground

class Test1 {
  @Test def t1(): Unit = {
    assertEquals("I was compiled by dotty :)", Playground.msg)
  }
}