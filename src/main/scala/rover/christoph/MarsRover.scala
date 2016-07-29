package rover.christoph

case class MarsRover() {

  val rightMap = Map("N" -> "E", "E" -> "S", "S" -> "W", "W" -> "N")
  val leftMap = rightMap.map(_.swap)
  val xMap = Map("N" -> 0, "E" -> 1, "S" -> 0, "W" -> -1)
  val yMap = Map("N" -> 1, "E" -> 0, "S" -> -1, "W" -> 0)

  def right() = {
    (position._1, position._2, rightMap(position._3))
  }

  def left() = {
    (position._1, position._2, leftMap(position._3))
  }

  def forward() = {
    (position._1 + xMap(position._3), position._2 + yMap(position._3), position._3)
  }

  def backward() = {
    (position._1 - xMap(position._3), position._2 - yMap(position._3), position._3)
  }

  def command(s: String) = {
    cmd(s)
  }

  private def cmd(s: String) = {
    if (s == "R") {
      position = right()
    }
    if (s == "L") {
      position = left()
    }
    if (s == "F") {
      position = forward()
    }
    if (s == "B") {
      position = backward()
    }
    this
  }

  var position = (0, 0, "N")

}
