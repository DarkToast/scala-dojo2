package anagram.christian

object Main extends App {
  val start = System.currentTimeMillis()

  val source = scala.io.Source.fromInputStream(getClass.getResourceAsStream("/wordlist.txt"))

  val result = Anagram.find(source.getLines().toList)

  val end = System.currentTimeMillis()

  println(result.toPrettyString)
  println(s"\n\nAnagrams found: ${result.anagramCount}")
  println(s"Longest anagrams: ${result.longestAnagrams}")
  println(s"Longest set of words: ${result.longestSet}")
  println(s"Runtime: ${end - start}ms")
}
