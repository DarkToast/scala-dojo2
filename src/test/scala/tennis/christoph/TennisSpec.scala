package tennis.christoph

import org.scalatest.FunSuite

class TennisSpec extends FunSuite {

  class Game(initialScore: String) {
    var score = initialScore
    val defaultScore = "0:0"

    val nextScoreServer = Map[String, String](
      "0:0" -> "15:0",
      "0:15" -> "15:15",
      "0:30" -> "15:30",
      "0:40" -> "15:40",
      "15:0" -> "30:0",
      "15:15" -> "30:15",
      "15:30" -> "30:30",
      "15:40" -> "30:40",
      "30:0" -> "40:0",
      "30:15" -> "40:15",
      "30:30" -> "40:30",
      "30:40" -> "40:40",
      "40:0" -> "Server wins",
      "40:15" -> "Server wins",
      "40:30" -> "Server wins",
      "40:40" -> "A:40",
      "40:A" -> "40:40",
      "A:40" -> "Server wins"
    )

    val nextScoreReceiver = Map[String, String](
      "0:0" -> "0:15",
      "0:15" -> "0:30",
      "0:30" -> "0:40",
      "0:40" -> "Receiver wins",
      "15:0" -> "15:15",
      "15:15" -> "15:30",
      "15:30" -> "15:40",
      "15:40" -> "Receiver wins",
      "30:0" -> "30:15",
      "30:15" -> "30:30",
      "30:30" -> "30:40",
      "30:40" -> "Receiver wins",
      "40:0" -> "40:15",
      "40:15" -> "40:30",
      "40:30" -> "40:40",
      "40:40" -> "40:A",
      "40:A" -> "Receiver wins",
      "A:40" -> "40:40"
    )

    def receiverScores() = {
      updateScore(nextScoreReceiver.getOrElse(score, defaultScore))
    }

    def serverScores() = {
      updateScore(nextScoreServer.getOrElse(score, defaultScore))
    }

    def updateScore(newScore: String): Game = {
      score = newScore
      this
    }
  }

  test("When score is invalid and server scores, score is 0:0") {
    def game = new Game("invalid")
    assertResult("0:0")(game.serverScores().score)
  }

  test("When score is 0:0 and server scores, score is 15:0") {
    def game = new Game("0:0")
    assertResult("15:0")(game.serverScores().score)
  }

  test("When score is 0:0 and server scores twice, score is 30:0") {
    def game = new Game("0:0")
    assertResult("30:0")(game.serverScores().serverScores().score)
  }

  test("When score is 0:0 and receiver scores, score is 0:15"){
    def game = new Game("0:0")
    assertResult("0:15")(game.receiverScores().score)
  }

  test("When score is 15:0 and server scores, score is 30:0") {
    def game = new Game("15:0")
    assertResult("30:0")(game.serverScores().score)
  }

  test("When score is 0:15 and receiver scores, score is 0:30") {
    def game = new Game("0:15")
    assertResult("0:30")(game.receiverScores().score)
  }

  test("When score is 30:0 and server scores, score is 40:0") {
    def game = new Game("30:0")
    assertResult("40:0")(game.serverScores().score)
  }

  test("When score is 0:30 and receiver scores, score is 0:40") {
    def game = new Game("0:30")
    assertResult("0:40")(game.receiverScores().score)
  }

  test("When score is 40:0 and server scores, server wins") {
    def game = new Game("40:0")
    assertResult("Server wins")(game.serverScores().score)
  }

  test("When score is 0:40 and receiver scores, receiver wins") {
    def game = new Game("0:40")
    assertResult("Receiver wins")(game.receiverScores().score)
  }

  test("When score is 40:30 and receiver scores, score is deuce") {
    def game = new Game("40:30")
    assertResult("40:40")(game.receiverScores().score)
  }

  test("When score is Deuce and server scores, score is advantage server") {
    def game = new Game("40:40")
    assertResult("A:40")(game.serverScores().score)
  }

  test("When score is Deuce and receiver scores, score is advantage receiver") {
    def game = new Game("40:40")
    assertResult("40:A")(game.receiverScores().score)
  }

  test("When score is Advantage server and server scores, server wins") {
    def game = new Game("A:40")
    assertResult("Server wins")(game.serverScores().score)
  }

  test("When score is Advantage receiver and receiver scores, receiver wins") {
    def game = new Game("40:A")
    assertResult("Receiver wins")(game.receiverScores().score)
  }

  test("When score is Advantage server and receiver scores, score is Deuce") {
    def game = new Game("A:40")
    assertResult("40:40")(game.receiverScores().score)
  }

  test("When score is Advantage receiver and server scores, score is Deuce") {
    def game = new Game("40:A")
    assertResult("40:40")(game.serverScores().score)
  }



}
