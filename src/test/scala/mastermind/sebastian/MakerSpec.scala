package mastermind.sebastian

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class MakerSpec extends FunSuite{


  test("Maker creates a valid code") {
    val maker = new Maker

    assert(maker.code == "")

    val code = maker.makeCode()

    code should fullyMatch regex """[A-F]{4}""".r
  }

  test("Maker evaluates the guess correctly") {
    val maker = new Maker("ABCD")

    var evaluation = maker.evaluateGuess("AAAA")
    assert(evaluation == "+---")

    evaluation = maker.evaluateGuess("AABB")
    assert(evaluation == "+---")

    evaluation = maker.evaluateGuess("DCBA")
    assert(evaluation == "----")

    evaluation = maker.evaluateGuess("ABCD")
    assert(evaluation == "++++")

    evaluation = maker.evaluateGuess("FFFF")
    assert(evaluation == "0000")
  }


}
