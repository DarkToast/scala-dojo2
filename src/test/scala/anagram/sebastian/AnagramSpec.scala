package anagram.sebastian

import mastermind.sebastian.Game
import org.scalatest.FunSuite

class AnagramSpec extends FunSuite {

  test("read testlist") {
    def finder = new AnagramFinder()
    finder.getAllAnagrams()

  }

  test("isAnagram") {
    def finder = new AnagramFinder()
    var ordered = finder.orderChars("aoidmod")
    assert(ordered == "addimoo")

    ordered = finder.orderChars("mnklacdbegfjih")
    assert(ordered == "abcdefghijklmn")
  }

}
