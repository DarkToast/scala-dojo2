package mastermind.christian

import Mastermind.{Answer, Code}

import scala.annotation.tailrec

sealed trait CodeSymbol
case object A extends CodeSymbol
case object B extends CodeSymbol
case object C extends CodeSymbol
case object D extends CodeSymbol
case object E extends CodeSymbol
case object F extends CodeSymbol

sealed trait AnswerSymbol
case object ++ extends AnswerSymbol
case object -- extends AnswerSymbol
case object / extends AnswerSymbol


object Mastermind {
  type Code = (CodeSymbol, CodeSymbol, CodeSymbol, CodeSymbol)
  type Answer = (AnswerSymbol, AnswerSymbol, AnswerSymbol, AnswerSymbol)

}

case class Mastermind(code: Code) {
  type CodeMap = Map[Int, CodeSymbol]
  type AnswerMap = Map[Int, AnswerSymbol]

  private val codeMap: Map[Int, CodeSymbol] = code match {
    case (a, b, c, d) => Map(1 -> a, 2 -> b, 3 -> c, 4 -> d)
  }

  @tailrec
  private def step(acc: AnswerMap, guessMap: CodeMap, symbolList: List[CodeSymbol], pos: Int): AnswerMap = {
    if(pos > 4) {
      acc
    } else {
      val symbol = guessMap(pos)

      if (codeMap(pos) == symbol) {
        step(acc + (pos -> ++), guessMap, symbolList diff List(symbol), pos + 1)
      } else if (symbolList.contains(symbol)) {
        step(acc + (pos -> --), guessMap, symbolList diff List(symbol), pos + 1)
      } else {
        step(acc + (pos -> /), guessMap, symbolList, pos + 1)
      }
    }
  }

  def tryGuess(guessing: Code): Answer = {
    val list = code match {
      case (a, b, c, d) => List(a, b, c, d)
    }

    val guessMap: CodeMap = guessing match {
      case (a, b, c, d) => Map(1 -> a, 2 -> b, 3 -> c, 4 -> d)
    }

    val answerMap: AnswerMap = step(Map(), guessMap, list, 1)
    (answerMap(1), answerMap(2), answerMap(3), answerMap(4))
  }
}
