package marsRover.christian_jacqueline

trait Direction {
  def unary_- : Direction
  def ↱ : Direction
  def ↰ : Direction
}

object North extends Direction {
  override def unary_- : Direction = South
  override def ↱ : Direction = East
  override def ↰ : Direction = West
}

object South extends Direction {
  override def unary_- : Direction = North
  override def ↱ : Direction = West
  override def ↰ : Direction = East
}

object West extends Direction {
  override def unary_- : Direction = East
  override def ↱ : Direction = North
  override def ↰ : Direction = South
}

object East extends Direction {
  override def unary_- : Direction = West
  override def ↱ : Direction = South
  override def ↰ : Direction = North
}
