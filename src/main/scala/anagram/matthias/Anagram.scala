package anagram.matthias

case class Anagram() {

  def isAnagram(word1: String, word2: String): Boolean = {
    val letters1 = word1.toCharArray
    val letters2 = word2.toCharArray
    letters2.diff(letters1).length == 0 && letters1.diff(letters2).length == 0
  }

}
