package mastermind.sebastian

class Maker (var code:String) {

  def this() {
   this("")
  }

  def makeCode(): String = {
    code="AAAA"
    return code
  }

  def evaluateGuess(guess:String):String = {
    var evaluation:String = ""
    for ( i <- 0 to 3) {
      if (code.charAt(i) == guess.charAt(i)) evaluation+="+"
      else if (code.contains(guess.charAt(i))) evaluation+="-"
      else evaluation+="0"
    }

    return evaluation
  }
}
