package anagram.christian

case class Result(anagrams: List[List[String]], longestAnagrams: Set[String], longestSet: Set[String]) {
  def anagramCount: Int = anagrams.size

  def toPrettyString: String = {
    anagrams.map(list => list.mkString(" ")).mkString("\n")
  }
}

object Anagram {
  def find(words: List[String]): Result = {
    val anagrams = anagramList(words)

    Result(anagrams, longestAnagrams(anagrams), longestSet(anagrams))

  }

  private def longestSet(anagrams: List[List[String]]): Set[String] = {
    if(anagrams.isEmpty) {
      Set()
    } else {
      val groupMap: Map[Int, List[Set[String]]] = anagrams.map(_.toSet).groupBy(_.size)
      val maxKey = groupMap.keys.max
      groupMap(maxKey).head
    }
  }

  private def longestAnagrams(anagrams: List[List[String]]): Set[String] = {
    if(anagrams.isEmpty) Set()
    else anagrams.sortWith((l1, l2) => l1.head.length > l2.head.length).head.toSet
  }

  private def anagramList(words: List[String]): List[List[String]] = {
    // word = List(kinship, pinkish)
    case class WordChars(content: String, chars: Seq[Char])

    // eg: List( WordChars(kinship, Seq(h, i, i, k, n, p, s)), WordChars(pinkish, Seq(h, i, i, k, n, p, s)))
    val wordChars: List[WordChars] = words.map(word => WordChars(word, word.toCharArray.sorted))

    //Map( Set(h, i, i, k, n, p, s)   ->   List(WordChars(kinship, Seq(h, i, i, k, n, p, s)), WordChars(pinkish, Seq(h, i, i, k, n, p, s)))  )
    val groupedWordChars: Map[Seq[Char], List[WordChars]] = wordChars.groupBy(word => word.chars)

    // List(List(kinship, pinkish))
    val anagramsList: List[List[String]] = groupedWordChars.values.map(wordList => wordList.map(word => word.content)).toList

    val emptyAnagramsFiltered = anagramsList.filter(_.size > 1)

    emptyAnagramsFiltered
  }
}