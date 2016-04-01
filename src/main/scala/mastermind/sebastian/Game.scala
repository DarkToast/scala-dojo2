package mastermind.sebastian

class Game {

  def main(args: Array[String]): Unit = {

    val maker = new Maker("FACB")
    val breaker = new Breaker()

    var recentGuess= "AAAA"
    println("nextGuess: "+recentGuess)
    var recentEvaluation= maker.evaluateGuess(recentGuess)

    while (recentEvaluation!="++++") {
      println("-> recentEvaluation: "+recentEvaluation)
      recentGuess = breaker.breakCode(recentGuess, recentEvaluation)
      println("nextGuess: "+recentGuess)
      recentEvaluation = maker.evaluateGuess(recentGuess)
      Thread.sleep(1000)
    }

  }

}
