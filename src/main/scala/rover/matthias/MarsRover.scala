package rover.matthias

case class MarsRover() {

  val rightMap = Map("N" -> "E", "E" -> "S", "S" -> "W", "W" -> "N")
  val leftMap = Map("N" -> "W", "W" -> "S", "S" -> "E", "E" -> "N")

  val xMap = Map("N" -> 0, "E" -> 1, "S" -> 0, "W" -> -1)
  val yMap = Map("N" -> 1, "E" -> 0, "S" -> -1, "W" -> 0)

  def command(s: String) = {
    if (s == "R") {
      position = (position._1, position._2, rightMap(position._3))
    }
    if (s == "L") {
      position = (position._1, position._2, leftMap(position._3))
    }
    if (s == "F") {
      position = ( position._1 + xMap(position._3), position._2 + yMap(position._3), position._3)
    }
    if (s == "B") {
      position = (position._1 - xMap(position._3), position._2 - yMap(position._3), position._3)
    }
    this
  }

  var position = (0, 0, "N")

}
