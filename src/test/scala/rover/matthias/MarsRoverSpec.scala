package rover.matthias

import org.scalatest.{FeatureSpec, GivenWhenThen, MustMatchers}

class MarsRoverSpec extends FeatureSpec with GivenWhenThen with MustMatchers {

  feature("mars rover basics") {

    scenario("initial state") {
      Given("a rover")
      def marsRover = MarsRover()

      When("i call get position")
      val position = marsRover.position

      Then("a position is returned")
      position must be(0, 0, "N")

    }

    scenario("rotating the rover right") {
      Given("a rover")
      def marsRover = MarsRover()

      When("i rotate the rover right once")
      val position = marsRover.command("R").position

      Then("a position is returned")
      position must be(0, 0, "E")

    }

    scenario("rotating the rover left") {
      Given("a rover")
      def marsRover = MarsRover()

      When("i rotate the rover left once")
      val position = marsRover.command("L").position

      Then("a position is returned")
      position must be(0, 0, "W")

    }

    scenario("rotating the rover left twice") {
      Given("a rover")
      def marsRover = MarsRover()

      When("i rotate the rover left once")
      val position = marsRover.command("L").command("L").position

      Then("a position is returned")
      position must be(0, 0, "S")

    }

    scenario("moving the rover forward") {
      Given("a rover")
      def marsRover = MarsRover()

      When("i move the rover forward once")
      val position = marsRover.command("F").position

      Then("a position is returned")
      position must be(0, 1, "N")
    }

    scenario("moving the rover backwards") {
      Given("a rover")
      def marsRover = MarsRover()

      When("i move the rover backwards twice")
      val position = marsRover.command("B").command("B").position

      Then("a position is returned")
      position must be(0, -2, "N")
    }

    scenario("rotating and moving") {
      Given("a rover")
      def marsRover = MarsRover()

      When("i turn the rover right and move forward once")
      val position = marsRover.command("R").command("F").position

      Then("a position is returned")
      position must be(1, 0, "E")
    }

    scenario("rotating and moving 2.0") {
      Given("a rover")
      def marsRover = MarsRover()

      When("I move F, turn right, move F, turn right twice, move backwards, turn left")
      val position = marsRover.command("F").command("R").command("F").command("R").command("R").command("B").command("L").position

      Then("a position is returned")
      position must be(2, 1, "S")
    }

  }

}