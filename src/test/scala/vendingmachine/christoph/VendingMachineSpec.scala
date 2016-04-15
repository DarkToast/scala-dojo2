package vendingmachine.christoph

import org.scalatest.FunSuite
import scala.math.BigDecimal

class VendingMachineSpec extends FunSuite {

  trait Coin { def value : BigDecimal; def character: String }

  object Nickel extends Coin { def value = 0.05; def character = "NICKEL" }
  object Dime extends Coin { def value = 0.10; def character = "DIME" }
  object Quarter extends Coin { def value = 0.25; def character = "QUARTER" }
  object Dollar extends Coin { def value = 1.00; def character = "DOLLAR" }

  trait Command {}
  trait Product { def cost: BigDecimal; def name: String }

  object CoinReturn extends Command {}
  object GetA extends Command with Product{ def cost = 0.65; def name = "A" }
  object GetB extends Command with Product{ def cost = 1.00; def name = "B" }
  object GetC extends Command with Product{ def cost = 1.50; def name = "C" }

  class VendingMachine() {

    private var deposit : List[Coin] = Nil

    def input(coins: List[Coin], cmd: Command) : String = {
      deposit = deposit ::: coins
      cmd match {
        case CoinReturn => deposit.map(_.character).mkString(", ")
        case p :Product =>  {
          val diff = diffDeposit( deposit, p )
          if( diff < 0 ) { "" } else if( diff == 0 ) { p.name } else { outputReturnedCoins( deposit, p) + ", " + p.name }
        }
      }
    }

    private def outputReturnedCoins(deposit: List[Coin], product: Product) : String = {
      returnedCoins(deposit, product).map(_.character).mkString(", ")
    }

    private def diffDeposit(deposit: List[Coin], p: Product): BigDecimal = {
      deposit.map(_.value).sum - p.cost
    }

    private def returnedCoins(deposit: List[Coin], product: Product) : List[Coin] = {
      var diff: BigDecimal = diffDeposit(deposit, product)
      var coins: List[Coin] = Nil

      while(diff >= Dollar.value) {
        diff = diff - Dollar.value
        coins ::= Dollar
      }
      while(diff >= Quarter.value) {
        diff = diff - Quarter.value
        coins ::= Quarter
      }
      while(diff >= Dime.value) {
        diff = diff - Dime.value
        coins ::= Dime
      }
      while(diff >= Nickel.value) {
        diff = diff - Nickel.value
        coins ::= Nickel
      }
      coins
    }
  }

  test("Return nothing if nothing is inserted and buyer aborts") {
    assertResult("")(new VendingMachine().input(Nil, CoinReturn))
  }

  test("Return 'NICKEL' if a Nickel is inserted and buyer aborts") {
    assertResult("NICKEL")(new VendingMachine().input(List(Nickel), CoinReturn))
  }

  test("Return 'NICKEL, DIME' if a Nickel and a Dime are inserted and buyer aborts") {
    assertResult("NICKEL, DIME")(new VendingMachine().input(List(Nickel, Dime), CoinReturn))
  }

  test("Return 'A' if two Quarters, a Dime and a Nickel are inserted and product A is bought") {
    assertResult("A")(new VendingMachine().input(List(Quarter, Quarter, Nickel, Dime), GetA))
  }

  test("Return '' if two Quarters are inserted and product A is bought") {
    assertResult("")(new VendingMachine().input(List(Quarter, Quarter), GetA))
  }

  test("Return 'B' if a Dollar is inserted and product B is bought") {
    assertResult("B")(new VendingMachine().input(List(Dollar), GetB))
  }

  test("Return 'C' if a Dollar and two Quarters are inserted and product C is bought") {
    assertResult("C")(new VendingMachine().input(List(Dollar, Quarter, Quarter), GetC))
  }

  test("Return 'DIME, A' if three Quarters are inserted and product A is bought") {
    assertResult("DIME, A")(new VendingMachine().input(List(Quarter, Quarter, Quarter), GetA))
  }

  test("Assure that all coins are returned after unsuccessful purchase") {
    val vm = new VendingMachine()
    assertResult("")(vm.input(List(Dime, Dime), GetA))
    assertResult("")(vm.input(List(Dime, Dime), GetA))
    assertResult("")(vm.input(List(Dime, Dime), GetA))
    assertResult("DIME, DIME, DIME, DIME, DIME, DIME")(vm.input(List(), CoinReturn))
  }

  test("Assert that deposit is kept after unsuccessful purchase") {
    val vm = new VendingMachine()
    assertResult("")(vm.input(List(Quarter, Quarter), GetA))
    assertResult("DIME, A")(vm.input(List(Quarter), GetA))
  }


}
