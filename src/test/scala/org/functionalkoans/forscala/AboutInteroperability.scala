package org.functionalkoans.forscala

import org.functionalkoans.forscala.support.KoanSuite
import org.scalatest.Matchers

class AboutInteroperability extends KoanSuite with Matchers {
  koan(
    """You can interop with a java class and it's use of collections by importing
      |   scala.collection.JavaConverters. This koan
      |   converts a scala List of String to java List of raw type.""") {

    import scala.collection.JavaConverters._
    val d = new SomeJavaClass
    val e = List("one", "two", "three")
    d.findSizeOfRawType(e.asJava) should be(3)
  }

  class Boat(size: Int, manufacturer: String)

  koan("""This koan converts a scala List of Boat (our own class) to java List of unknown <?> type.""") {
    import scala.collection.JavaConverters._
    val d = new SomeJavaClass
    val e = List(new Boat(33, "Skyway"), new Boat(35, "New Boat"))
    d.findSizeOfUnknownType(e.asJava) should be(2)
  }
}
