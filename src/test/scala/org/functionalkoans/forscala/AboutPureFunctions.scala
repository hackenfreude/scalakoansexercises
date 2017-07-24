package org.functionalkoans.forscala

import org.functionalkoans.forscala.support.KoanSuite
import org.scalatest.Matchers


/** = Pure functions =
  * For these exercises we will only be using ''pure'' functions. These are functions that take one or more arguments
  * and return an argument. Pure functions can not change state!
  * <p>To enforce the use of pure functions, we will be writing them in an object (let's call it a module) and we
  * will not keep ''any'' state.
  * <p>Also, we will only be using function notation. This means '''no''' `def`s :) Why this is will be explained in
  * a later exercise.
  */
class AboutPureFunctions extends KoanSuite with Matchers {
  import PureFunctionModule._

  koan("""Although written the differently, they mean the same""") {
    // one way of writing: define types for the arguments, return type will be inferred
    doubleMe(5) should be(10)

    // defines the function prototype, argument types need not be redefined
    `doubleMe'`(8) should be(16)
  }

  koan("""Defining multiple arguments works a bit differently""") {
    // like doubleMe, nothing special here
    doubleUs(3, 4) should be(14)

    // argument types defined in the function prototype, notice the use of `A => B => R` (arg1 => arg2 => return)
    `doubleUs'`(3)(4) should be(14)
  }

  koan("""We want to be DRY, so let's reuse a previously defined function""") {
    composedDoubleUs(3)(4) should be(14)
    composedDoubleUs(7)(7) should be(28)
  }

  koan(
    """Multiply a number by 2, but only if it is smaller than or equal to 100,
      | because numbers bigger than 100 are big enough as it is""".stripMargin) {
    doubleSmallNumber(5) should be(10)
    doubleSmallNumber(120) should be(120)

    // if we wanted to add 1 to each number produced in our previous function, we could have done it like this
    `doubleSmallNumber'`(171) should be(172)
    `doubleSmallNumber'`(21) should be(43)
  }
}


object PureFunctionModule {

  val doubleMe = (x: Int) ⇒ x + x
  val `doubleMe'`: Int ⇒ Int = x ⇒ x + x

  val doubleUs = (x: Int, y: Int) ⇒ x * 2 + y * 2
  val `doubleUs'`: Int ⇒ Int ⇒ Int = x ⇒ y ⇒ x * 2 + y * 2

  val composedDoubleUs: Int ⇒ Int ⇒ Int = x ⇒ y ⇒ doubleMe(x) + doubleMe(y)

  /** As a function always needs to return something, in a ''pure''-functional language, the ``else`` to an `if` is
    * mandatory.
    * Although scala is not ''pure''-functional, we are going to write only ''pure'' functions here. When used in
    * this way, an `if` statement will always return a value and that's why it is an expression.
    */
  val doubleSmallNumber = (x: Int) ⇒ if (x > 100) x else x * 2
  val `doubleSmallNumber'`: Int ⇒ Int = x ⇒ (if (x > 100) x else x * 2) + 1
}
