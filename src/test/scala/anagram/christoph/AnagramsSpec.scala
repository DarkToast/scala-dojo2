package anagram.christoph

import java.io.File

import org.scalatest.FunSuite

import scala.io.Source

class AnagramsSpec extends FunSuite {


  def words(): List[String] = {
    val filename = "/home/christoph/projects/scala-dojo2/src/test/scala/anagram/christoph/wordlist.txt"
    val file = new File(filename)
    assertResult(true)(file.exists())
    Source.fromFile(file).getLines().toList
  }

  def isAnagram(s: String, t: String): Boolean = {
    s.toCharArray().diff(t.toCharArray()).length == 0 && t.toCharArray().diff(s.toCharArray()).length == 0
  }

  def getAnagramsOf(s: String, lines: List[String]): String = {
    val matchingLines = lines.filter(isAnagram(_, s))
    matchingLines.mkString(" ")
  }

  test("test if lamp is anagram of palm") {
    assertResult(true)(isAnagram("lamp", "palm"))
  }

  test("test if that is anagram of hat") {
    assertResult(false)(isAnagram("that", "hat"))
  }

  test("test if fish is anagram of hi") {
    assertResult(false)(isAnagram("fish", "hi"))
  }

  test("find anagram for kinship") {
    var wordlist = List("kinship", "pinkish")
    assertResult("kinship pinkish")(getAnagramsOf("kinship", wordlist))
  }

  test("find anagram for kinship in wordlist") {
    var wordlist = List("kinship", "pinkish")
    assertResult("kinship pinkish")(getAnagramsOf("kinship", words()))
  }


}
