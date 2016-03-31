package mastermind.christian

import mastermind.christian.Mastermind.Code
import org.scalatest.{FeatureSpec, GivenWhenThen, MustMatchers}


class MastermindSpec extends FeatureSpec with GivenWhenThen with MustMatchers {
  val code: Code = (B, C, D, E)
  val code2: Code = (B, B, C, C)

  feature("A guessing") {
    scenario("(A, A, A, A) returns (/, /, /, /)") {
      val guess = (A, A, A, A)
      Mastermind(code).tryGuess(guess) must be (/, /, /, /)
    }

    scenario("(E, D, C, B) returns (-, -, -, -)") {
      val guess = (E, D, C, B)
      Mastermind(code).tryGuess(guess) must be (--, --, --, --)
    }

    scenario("(B, C, D, E) returns (+, +, +, +)") {
      val guess = (B, C, D, E)
      Mastermind(code).tryGuess(guess) must be (++, ++, ++, ++)
    }

    scenario("(E, C, D, B) returns (-, +, +, -)") {
      val guess = (E, C, D, B)
      Mastermind(code).tryGuess(guess) must be (--, ++, ++, --)
    }

    scenario("(A, F, D, E) returns (/, /, +, +)") {
      val guess = (A, F, D, E)
      Mastermind(code).tryGuess(guess) must be (/, /, ++, ++)
    }

    scenario("(A, B, D, E) returns (-, +, +, +)") {
      val guess = (A, B, D, E)
      Mastermind(code).tryGuess(guess) must be (/, --, ++, ++)
    }

    scenario("(B, B, D, E) returns (+, /, +, +)") {
      val guess = (B, B, D, E)
      Mastermind(code).tryGuess(guess) must be (++, /, ++, ++)
    }

    scenario("(B, D, C, C) returns (-, +, +, -)") {
      val guess = (B, D, C, C)
      Mastermind(code).tryGuess(guess) must be (++, --, --, /)
    }

    scenario("(B, B, C, C) returns (+, +, +, +) on the code2") {
      val guess = (B, B, C, C)
      Mastermind(code2).tryGuess(guess) must be (++, ++, ++, ++)
    }

    scenario("(C, C, B, B) returns (-, -, -, -) on the code2") {
      val guess = (C, C, B, B)
      Mastermind(code2).tryGuess(guess) must be (--, --, --, --)
    }

    scenario("(B, B, B, B) returns (+, +, /, /) on the code2") {
      val guess = (B, B, B, B)
      Mastermind(code2).tryGuess(guess) must be (++, ++, /, /)
    }
  }
}
