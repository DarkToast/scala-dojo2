package marsRover.christian_jacqueline

case class Point(x: Int, y: Int) {
  val maxX = 100
  val maxY = 100

  def oneStepTo(direction: Direction): Point = {
    direction match {
      case North => toNorth()
      case South => toSouth()
      case West => toWest()
      case East => toEast()
    }
  }

  private def toNorth(): Point = {
    this.copy(y = (y + 1) % maxY)
  }

  private def toSouth(): Point = {

    this.copy(y = if(y - 1 < 0) maxY -1 else y - 1)
  }

  private def toWest(): Point = {
    this.copy(x = if(x - 1 < 0) maxX -1 else x - 1)
  }

  private def toEast(): Point = {
    this.copy(x = (x + 1) % maxX)
  }
}
