package tennis.sebastian

class Game {

  var state: Int = 0
  var serverScore: Int = 0
  var receiverScore: Int = 0

  def setScore = (x: Int, y: Int) => {
    serverScore = x
    receiverScore = y
  }

  def setAdvantageServer = {
    serverScore = 50
    receiverScore = 40
  }

  def setAdvantageReceiver = {
    serverScore = 40
    receiverScore = 50
  }

  def getScore = translate(serverScore)+":"+translate(receiverScore)

  def receiverScores = {
    if (receiverScore == 0) receiverScore = 15
    else if (receiverScore == 15) receiverScore = 30
    else if (receiverScore == 30) receiverScore = 40
    else if (receiverScore == 40) {
      if (serverScore < 40) state = 2
      else if (serverScore == 40) receiverScore = 50
    }

  }

  def serverScores = {
    if (serverScore == 0) serverScore = 15
    else if (serverScore == 15) serverScore = 30
    else if (serverScore == 30) serverScore = 40
    else if (serverScore == 40) {
      if (receiverScore < 40) state = 1
      else if (receiverScore == 40) serverScore = 50
    }
  }

  def translate(x:Int): String = {
    if (x==50) "A"
    else ""+x
  }

}
