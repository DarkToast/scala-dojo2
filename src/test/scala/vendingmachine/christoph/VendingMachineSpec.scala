package vendingmachine.christoph

import org.scalatest.FunSuite

class VendingMachineSpec extends FunSuite {

  trait Coin { def value : Float; def character: String }

  object Nickel extends Coin { def value = 0.05F; def character = "NICKEL" }
  object Dime extends Coin { def value = 0.10F; def character = "DIME" }
  object Quarter extends Coin { def value = 0.25F; def character = "QUARTER" }
  object Dollar extends Coin { def value = 1.00F; def character = "DOLLAR" }

  trait Command {}
  trait Product { def cost: Float; def name: String }

  object CoinReturn extends Command {}
  object GetA extends Command with Product{ def cost = 0.65F; def name = "A" }
  object GetB extends Command with Product{ def cost = 1.00F; def name = "B" }
  object GetC extends Command with Product{ def cost = 1.50F; def name = "C" }

  class VendingMachine() {

    private var deposit: List[Coin] = Nil
    private var command: Command = null

    def input(coins: List[Coin], cmd: Command) : VendingMachine = {
      deposit = coins
      command = cmd
      this
    }

    def output() : String = {
      command match {
        case CoinReturn => outputDeposit()
        case product :Product =>  {
          val diff = diffDeposit(product)
          if( diff < 0 ) { "" } else {
            val coins = returnedCoins(product)
            if( coins.isEmpty ) {
              product.name
            } else {
              outputReturnedCoins(product) + ", " + product.name }
            }

        }
      }
    }

    def outputDeposit() : String = {
      deposit.map(_.character).mkString(", ")
    }

    def outputReturnedCoins(product: Product) : String = {
      returnedCoins(product).map(_.character).mkString(", ")
    }

    def sumDeposit() : Float = {
      deposit.map(_.value).sum
    }

    def diffDeposit(p: Product): Float = {
      sumDeposit() - p.cost
    }

    def returnedCoins(product: Product) : List[Coin] = {
      var diff: Float = diffDeposit(product)
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
  def vendingMachine(coins: List[Coin], command: Command): String = {
    ""
  }

  test("Return nothing if nothing is inserted and buyer aborts") {
    assertResult("")(new VendingMachine().input(Nil, CoinReturn).output())
  }

  test("Return 'NICKEL' if a Nickel is inserted and buyer aborts") {
    assertResult("NICKEL")(new VendingMachine().input(List(Nickel), CoinReturn).output())
  }

  test("Return 'NICKEL, DIME' if a Nickel and a Dime are inserted and buyer aborts") {
    assertResult("NICKEL, DIME")(new VendingMachine().input(List(Nickel, Dime), CoinReturn).output())
  }

  test("Return 'A' if two Quarters, a Dime and a Nickel are inserted and product A is bought") {
    assertResult("A")(new VendingMachine().input(List(Quarter, Quarter, Nickel, Dime), GetA).output())
  }

  test("Return '' if two Quarters are inserted and product A is bought") {
    assertResult("")(new VendingMachine().input(List(Quarter, Quarter), GetA).output())
  }

  test("Return 'B' if a Dollar is inserted and product B is bought") {
    assertResult("B")(new VendingMachine().input(List(Dollar), GetB).output())
  }

  test("Return 'C' if a Dollar and two Quarters are inserted and product C is bought") {
    assertResult("C")(new VendingMachine().input(List(Dollar, Quarter, Quarter), GetC).output())
  }

  test("Return 'DIME, A' if three Quarters are inserted and product A is bought") {
    assertResult("DIME, A")(new VendingMachine().input(List(Quarter, Quarter, Quarter), GetA).output())
  }

}
