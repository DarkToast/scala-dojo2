package marsRover.christian_jacqueline

import org.scalatest.{FeatureSpec, GivenWhenThen, MustMatchers}

class RoverSpec extends FeatureSpec with GivenWhenThen with MustMatchers {

  feature("The rover moves") {
    scenario("The rover starts at its given position") {
      Given("The rover at position 50, 50")
      val rover: Rover = new Rover(Point(50, 50))

      When("We call the actual position of the rover")
      val actual: Point = rover.actualPosition()

      Then("The point is on 50,50")
      actual.x mustBe 50
      actual.y mustBe 50
    }

    scenario("The rover starts with north direction") {
      Given("The rover at position 50, 50")
      val rover: Rover = new Rover(Point(50, 50))

      When("We call the actual position of the rover")
      val actual: Direction = rover.actualDirection()

      Then("The point is on 50,50")
      actual mustBe North
    }

    scenario("The rover moves to West and moves one step forward") {
      Given("The rover at position 50, 50")
      var rover: Rover = new Rover(Point(50, 50))

      When("We call move left method")
      rover = rover ↰

      And("We call the move forward method")
      rover = rover ↥

      Then("The point is on 49,50")
      rover.actualPosition() mustBe Point(49,50)
    }

    scenario("The rover moves across the mars") {
      Given("The rover at position 50, 50")
      var rover: Rover = new Rover(Point(50, 50))

      When("We call several move and forward methods")
      rover = rover.↰.↥.↥.↱.↥.↱.↥.↱.↥.↥

      Then("The point is on 49,49")
      rover.actualPosition() mustBe Point(49,49)
    }

    scenario("The rover switches the position from 100 to 0") {
      Given("The rover at position 99, 99")
      var rover: Rover = new Rover(Point(99, 99), North)

      When("We rovers moves one point to North and one point to East")
      rover = rover.↥.↱.↥

      Then("The point is on 49,49")
      rover.actualPosition() mustBe Point(0,0)
    }

    scenario("The rover switches the position from 0 to 99") {
      Given("The rover at position 99, 99")
      var rover: Rover = new Rover(Point(0, 0), South)

      When("We rovers moves one point to North and one point to East")
      rover = rover.↥.↱.↥

      Then("The point is on 49,49")
      rover.actualPosition() mustBe Point(99,99)
    }
  }

}
