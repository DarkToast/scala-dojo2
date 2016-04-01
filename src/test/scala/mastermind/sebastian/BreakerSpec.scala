package mastermind.sebastian

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class BreakerSpec extends FunSuite{

  test("Breaker guesses make sense") {
    val breaker = new Breaker()

    //Zeros are not gonna be tested again. Plusses stay where they are
    var recentResponse = "+++0"
    breaker.localIndex(3) += 'A'
    var recentGuess = "ABCD"
    var guess = breaker.breakCode(recentGuess, recentResponse)
    guess should startWith regex """ABC"""
    assert (guess.charAt(3)!='A')

    //Minuses are guessed at a different index again
    recentResponse = "-000"
    recentGuess = "ABCD"
    guess = breaker.breakCode(recentGuess, recentResponse)
    assert (guess.charAt(0)!='A')
    assert (guess.contains('A'))
  }


}
