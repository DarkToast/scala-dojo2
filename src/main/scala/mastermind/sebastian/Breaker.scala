package mastermind.sebastian

class Breaker {

  var globalIndex = scala.collection.mutable.Set[Char]()
  var localIndex = new Array[scala.collection.mutable.Set[Char]](4)

  def init() {
    for (i <- 0 to 3) {
      this.localIndex(i) = scala.collection.mutable.Set[Char]()
    }
  }

  def breakCode(recentGuess:String, recentEvaluation:String):String = {
    var nextGuess:String=""

    for ( i <- 0 to 3) {
      val recentChar = recentGuess.charAt(i)
      val recentMatch = recentEvaluation.charAt(i)
      if (recentMatch == '0') {
        globalIndex += recentChar
        nextGuess+=guessPossibleChar(i)
      }
      else if (recentMatch == '-') {
        localIndex(i) += recentChar
        nextGuess+=guessPossibleChar(i)
      }
      else nextGuess+=recentChar
    }
    return nextGuess
  }

  def guessPossibleChar(position:Int): Char = {
    for (x <- 'A' to 'F') {
      if (!globalIndex.contains(x) && !localIndex(position).contains(x)) {
        return x
      }
    }

    throw new RuntimeException("Not possible");
  }

  init()
}
