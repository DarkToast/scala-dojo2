package christian.tennis


object TennisMatch {
  def apply(serverScore: TennisScore, receiverScore: TennisScore): TennisMatch = Running(serverScore, receiverScore)
}

sealed trait TennisMatch {
  def serverScores(): TennisMatch
  def receiverScores(): TennisMatch
}

case class Running(serverScore: TennisScore, receiverScore: TennisScore) extends TennisMatch  {

  override def receiverScores(): TennisMatch = (serverScore, receiverScore) match {
    case (A, Fourty)               => Running(Fourty, Fourty)
    case (Fourty, Fourty)          => Running(Fourty, A)
    case (_, Fourty) | (Fourty, A) => ReceiverWins
    case _                         => Running(serverScore, receiverScore.next)
  }

  override def serverScores(): TennisMatch = (serverScore, receiverScore) match {
    case (Fourty, A)               => Running(Fourty, Fourty)
    case (Fourty, Fourty)          => Running(A, Fourty)
    case (A, Fourty) | (Fourty, _) => ServerWins
    case _                         => Running(serverScore.next, receiverScore)
  }
}

case object ServerWins extends TennisMatch {
  override def receiverScores(): TennisMatch = ServerWins
  override def serverScores(): TennisMatch = ServerWins
}

case object ReceiverWins extends TennisMatch {
  override def receiverScores(): TennisMatch = ReceiverWins
  override def serverScores(): TennisMatch = ReceiverWins
}