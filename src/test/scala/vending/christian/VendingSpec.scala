package vending.christian

import org.scalatest.{FeatureSpec, GivenWhenThen, MustMatchers}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer


class VendingSpec extends FeatureSpec with GivenWhenThen with MustMatchers {

  sealed trait Coin {
    val value: Double
  }

  object Nickel extends Coin {
    val value = 0.05
  }

  object Dime extends Coin {
    val value = 0.10
  }

  object Quarter extends Coin {
    val value = 0.25
  }

  object Dollar extends Coin {
    val value = 1.00
  }

  sealed trait Item {
    val value: Double
  }
  object A extends Item {
    val value: Double = 0.65
  }
  object B extends Item {
    val value: Double = 1.00
  }
  object C extends Item {
    val value: Double = 1.5
  }

  object Vending {
    val items: ListBuffer[Item] = mutable.ListBuffer()
    val coins: ListBuffer[Coin] = mutable.ListBuffer()

    def insertMoney(money: Coin) = ???

    def coinReturn: List[Coin] = ???

    def getA: Either[String, Item] = {
      ???
    }

    def getB: Either[String, Item] = ???
    def getC: Either[String, Item] = ???

    def service(setItems: List[Item]) = ???
  }


  feature("Winning a Point Increases Score Correctly") {

    scenario("getting an A without money gets an error") {
      Vending.getA mustBe Left("Not enough money")
    }

    scenario("getting an B without money gets an error") {
      Vending.getB mustBe Left("Not enough money")
    }

    scenario("getting an C without money gets an error") {
      Vending.getC mustBe Left("Not enough money")
    }


  }

}
