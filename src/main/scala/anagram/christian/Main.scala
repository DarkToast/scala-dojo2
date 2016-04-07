package anagram.christian

object Main extends App {
  val start = System.currentTimeMillis()

  val source = scala.io.Source.fromInputStream(getClass.getResourceAsStream("/wordlist.txt"))

  val result = Anagram.find(source.getLines().toList)

  println(result.toPrettyString)


  println(s"\n\nAnagrams found: ${result.anagramCount}")

  val end = System.currentTimeMillis()

  println(s"Runtime: ${end - start}ms")
}
