package tennis.christian

/**
  * Scores
  */
sealed trait TennisScore {
  def next: TennisScore
}
case object Zero extends TennisScore {
  override def next: TennisScore = Fifteen
}
case object Fifteen extends TennisScore {
  override def next: TennisScore = Thirty
}
case object Thirty extends TennisScore {
  override def next: TennisScore = Fourty
}
case object Fourty extends TennisScore {
  override def next: TennisScore = A
}
case object A extends TennisScore {
  override def next: TennisScore = Fourty
}

