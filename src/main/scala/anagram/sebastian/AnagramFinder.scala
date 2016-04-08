package anagram.sebastian

import scala.collection.immutable.HashMap
import scala.collection.mutable

class AnagramFinder {

  val wordArray:Array[String] = io.Source.fromInputStream(getClass.getResourceAsStream("/wordlist.txt")).getLines().toArray
  var anagramMap:Map[String, mutable.MutableList[String]] = new HashMap[String, mutable.MutableList[String]]

  def preProcessMap(): Unit = {
    io.Source.fromInputStream(getClass.getResourceAsStream("/wordlist.txt"))
      .getLines()
      .foreach(x => {
        val ordered:String = orderChars(x).toString;
        if (anagramMap.contains(ordered))
          anagramMap.get(ordered).get+=x
        else {
          anagramMap += (ordered -> new mutable.MutableList[String])
          anagramMap.get(ordered).get+=x
        }
      })
  }

  def getAllAnagrams(): Unit = {
    val startOutput = System.currentTimeMillis()
    preProcessMap()
    var count:Int=0
    anagramMap.values.
      filter(x => x.size > 1)
      .foreach(x => {println(x.mkString(" ")); count=count+1} )
    println("Count: "+count)
    println("Time: "+(System.currentTimeMillis()-startOutput) +"ms")
  }

  def orderChars(input:String): String = {
    return input.toCharArray.sortWith(_<_).mkString
  }
}
