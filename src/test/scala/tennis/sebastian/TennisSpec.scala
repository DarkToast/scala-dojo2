package tennis.sebastian

import org.scalatest.FunSuite

class TennisSpec extends FunSuite {


  test("Winning a Point Increases Score Correctly") {
    val game = new Game

    //Given the score is 0:0
    game.setScore(0, 0)
    //When the server wins a point
    game.serverScores
    //Then the score is 15:0
    assert(game.getScore == "15:0")

    //Given the score is 15:15
    game.setScore(15, 15)
    //When the receiver wins a point
    game.receiverScores
    //Then the score is 15:30
    assert(game.getScore == "15:30")

    //Given the score is 30:30
    game.setScore(30, 30)
    //When the server wins a point
    game.serverScores
    //Then the score is 40:30
    assert(game.getScore == "40:30")
  }


  test("Deuce and Advantage are Scored Correctly") {
    /*  As a library user
    I want deuce and advantage to be scored correctly
    So that I can display the score correctly*/

    val game = new Game

    //Given the score is 40: 40
    game.setScore(40, 40)
    //When the receiver wins a point
    game.receiverScores
    //Then the score should be 40: A
    assert(game.getScore == "40:A")

    //Given the score is A: 40
    game.setAdvantageServer
    //When the receiver wins a point
    game.receiverScores
    //Then the score should be 40: 40
    assert(game.getScore == "40:40")
  }


  test("Winning Points are Scored Correctly") {
    /* As a library user I want the winning point to be scored correctly
  So that I can display the winner*/

    val game = new Game
    assert(game.state == 0)

    //Given the score is 40:30
    game.setScore(40, 30)
    //When the server wins a point
    game.serverScores
    //Then the server should win
    assert(game.state == 1)

    //Given the score is 40:A
    game.setAdvantageReceiver
    //When the receiver wins a point
    game.receiverScores
    //Then the receiver should win
    assert(game.state == 2)
  }
}
