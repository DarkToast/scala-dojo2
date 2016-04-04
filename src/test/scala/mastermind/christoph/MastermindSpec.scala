package mastermind.christoph

import org.scalatest.FunSuite

class MastermindSpec extends FunSuite {

  def break(code: String, guess:String): String = {
    def numHits(code: String, guess: String): Integer = {
      var k = 0
      for( i <- 0 to 3 if code(i) == guess(i)) {
        k = k + 1
      }
      k
    }

    def numAlmost(code:String, guess:String): Integer = {
      var k = 0
      for( i <- 0 to 3 if code(i) != guess(i) && code.contains(guess(i))) {
        k = k + 1
      }
      k
    }

    def numMisses(code:String, guess:String): Integer = {
      var k = 0
      for( i <- 0 to 3 if code(i) != guess(i) && !(code.contains(guess(i)))) {
        k = k + 1
      }
      k
    }

    "0"*numMisses(code,guess) +
    "-"*numAlmost(code, guess) +
    "+"*numHits(code, guess)
  }

  test("When the code is AAAA, guess AAAA returns ++++") {
    assertResult("++++")(break("AAAA", "AAAA"))
  }

  test("When the code is AAAA, guess BBBB returns 0000") {
    assertResult("0000")(break("AAAA", "BBBB"))
  }

  test("When the code is AAAA, guess AAAB returns 0+++") {
    assertResult("0+++")(break("AAAA", "AAAB"))
  }

  test("When the code is AAAB, guess BAAA returns --++") {
    assertResult("--++")(break("AAAB", "BAAA"))
  }

  test("When the code is ABCD, guess ABEC returns 0-++") {
    assertResult("0-++")(break("ABCD", "ABEC"))
  }

  ignore("When the code is ABCD, guess AAEF returns 000+") {
    assertResult("000+")(break("ABCD", "AAEF"))
  }

}
