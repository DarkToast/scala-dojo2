package mastermind.sebastian

import org.scalatest.FunSuite

class GameSpec extends FunSuite {

  test("Play game") {
    def game = new Game()
    game.main(new Array[String](0))

  }

}
