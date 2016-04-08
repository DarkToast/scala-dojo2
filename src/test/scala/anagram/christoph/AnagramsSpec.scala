package anagram.christoph

import java.io.File

import org.scalatest.FunSuite

import scala.io.Source

class AnagramsSpec extends FunSuite {


  def words(): List[String] = {
    val file = new File("/home/christoph/projects/scala-dojo2/src/test/scala/anagram/christoph/wordlist.txt")
    Source.fromFile(file).getLines().toList.distinct
  }

  def hash(word: String) : String = {
    word.toCharArray().sorted.mkString("")
  }

  def getAnagramsIn(strings: List[String]) = {
    var map = scala.collection.mutable.Map[String,String]()
    for(word <- strings) {
      var index = hash(word)
      map(index) = if(map.contains(index)) map(index) + " " + word else word
    }
    map.filter(_._2.contains(" ")).toList.sortBy(_._1)
  }

  test("Hash of hat is aht") {
    assertResult("aht")(hash("hat"))
  }

  test("Hash of that is ahtt") {
    assertResult("ahtt")(hash("that"))
  }

  test("find all anagrams in wordlist") {
    assertResult(20683)(getAnagramsIn(words()).length)
  }
}
