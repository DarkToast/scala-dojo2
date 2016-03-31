package christian.tennis

import org.scalatest.{MustMatchers, GivenWhenThen, FeatureSpec}


class TennisSpec extends FeatureSpec with GivenWhenThen with MustMatchers {

  feature("Winning a Point Increases Score Correctly") {
    scenario("15 : 0") {
      Given("The score is 0:0")

      val tMatch = TennisMatch(Zero, Zero)

      When("The server wins a point")
      val actual = tMatch.serverScores()

      Then("The score is 15:0")
      actual mustBe Running(Fifteen, Zero)
    }

    scenario("15 : 30") {
      Given("the score is 15:15")
      val tMatch = TennisMatch(Fifteen, Fifteen)

      When("the receiver wins a point")
      val actual = tMatch.receiverScores()

      Then("the score is 15:30")
      actual mustBe Running(Fifteen, Thirty)
    }

    scenario("40 : 30") {
      Given("the score is 30:30")
      val tMatch = TennisMatch(Thirty, Thirty)

      When("the server wins a point")
      val actual = tMatch.serverScores()

      Then("the score is 40:30")
      actual mustBe Running(Fourty, Thirty)
    }
  }



  feature("Deuce and Advantage are Scored Correctly") {
    scenario("40 : A") {
      Given("the score is 40:40")
      val tMatch = TennisMatch(Fourty, Fourty)

      When("the receiver wins a point")
      val actual = tMatch.receiverScores()

      Then("The score should be 40:A")
      actual mustBe Running(Fourty, A)
    }

    scenario("Deuce for receiver") {
      Given("the score is A:40")
      val tMatch = TennisMatch(A, Fourty)

      When("the receiver wins a point")
      val actual = tMatch.receiverScores()

      Then("the score should be 40:40")
      actual mustBe Running(Fourty, Fourty)
    }

    scenario("Deuce for server") {
      Given("the score is 40:A")
      val tMatch = TennisMatch(Fourty, A)

      When("the server wins a point")
      val actual = tMatch.serverScores()

      Then("the score should be 40:40")
      actual mustBe Running(Fourty, Fourty)
    }
  }



  feature("Winning Points are Scored Correctly") {
    scenario("Server wins") {
      Given("the score is 40:30")
      val tMatch = TennisMatch(Fourty, Thirty)

      When("the server wins a point")
      val actual = tMatch.serverScores()

      Then("the server should win")
      actual mustBe ServerWins
    }

    scenario("Server wins also") {
      Given("the score is 40:30")
      val tMatch = TennisMatch(Fourty, Thirty)

      When("the server wins a point")
      val actual = tMatch.serverScores()

      Then("the server should win")
      actual mustBe ServerWins
    }

    scenario("Server wins again") {
      Given("the score is 40:15")
      val tMatch = TennisMatch(Fourty, Fifteen)

      When("the server wins a point")
      val actual = tMatch.serverScores()

      Then("the server should win")
      actual mustBe ServerWins
    }

    scenario("Receiver wins") {
      Given("the score is 40:A")
      val tMatch = TennisMatch(Fourty, A)

      When("the receiver wins a point")
      val actual = tMatch.receiverScores()

      Then("the receiver should win")
      actual mustBe ReceiverWins
    }

    scenario("Receiver wins also") {
      Given("the score is 30:40")
      val tMatch = TennisMatch(Fourty, A)

      When("the receiver wins a point")
      val actual = tMatch.receiverScores()

      Then("the receiver should win")
      actual mustBe ReceiverWins
    }

    scenario("Receiver wins again") {
      Given("the score is 40:15")
      val tMatch = TennisMatch(Fifteen, Fourty)

      When("the server wins a point")
      val actual = tMatch.receiverScores()

      Then("the server should win")
      actual mustBe ReceiverWins
    }
  }

}
