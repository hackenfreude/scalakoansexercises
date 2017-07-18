package org.functionalkoans.forscala

import org.functionalkoans.forscala.support.KoanSuite
import org.scalatest.Matchers

class AboutFormatting extends KoanSuite with Matchers {

  koan("String can be placed in format") {
    val s = "Hello World"
    "Application %s".format(s) should be("Application Hello World")
  }

  koan("Character Literals can be an a single character") {
    val a = 'a'
    val b = 'B'

    //format(a) is a string format, meaning the "%c".format(x)
    //will return the string representation of the char.

    "%c".format(a) should be("a")
    "%c".format(b) should be("B")

  }

  /** @note beware that octal values are highly deprecated and discouraged in any language! */
  koan("Character Literals can be an escape sequence, including octal or hexidecimal") {

    val c = '\u0061' //unicode for a
    val d = '\141' //octal for a
    val e = '\"'
    val f = '\\'

    "%c".format(c) should be("a")
    "%c".format(d) should be("a")
    "%c".format(e) should be("\"")
    "%c".format(f) should be("\\")
  }

  koan("Formatting can also include numbers") {
    val j = 190
    "%d bottles of beer on the wall" format j - 100 should be("90 bottles of beer on the wall")
  }

  koan("Formatting can be used for any number of items, like a string and a number") {
    val j = 190
    val k = "vodka"

    "%d bottles of %s on the wall".format(j - 100, k) should be("90 bottles of vodka on the wall")
  }

  koan("Formatting can be done inline using the 's' macro on a string") {
    val j = 190
    val k = "vodka"

    s"$j bottles of $k on the wall" should be("190 bottles of vodka on the wall")
  }

  koan("Formatting with the 's' macro can also be used for expressions") {
    val j = 190
    val k = "vodka"

    s"${j - 100} bottles of $k on the wall" should be("90 bottles of vodka on the wall")
  }
}
