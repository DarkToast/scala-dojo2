package marsRover.christian_jacqueline

case class Rover(private var position: Point, private var direction: Direction = North) {

  def ↰(): Rover = this.copy(direction = this.direction ↰)

  def ↱(): Rover = this.copy(direction = this.direction ↱)

  def ↥(): Rover = this.copy(position = position.oneStepTo(direction))

  def ↧(): Rover = this.copy(position = position.oneStepTo(-direction))

  def actualPosition(): Point = position.copy()

  def actualDirection(): Direction = direction
}






