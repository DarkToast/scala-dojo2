package anagram.matthias

import org.scalatest.{FeatureSpec, GivenWhenThen, MustMatchers}

class AnagramsSpec extends FeatureSpec with GivenWhenThen with MustMatchers {

  feature("findingn a anagram") {

    scenario("equal words") {
      Given("a word")
      val word1 = "rots"

      When("the same word is given")
      val word2 = "rots"

      Then("the result is true")
      Anagram().isAnagram(word1,word2) must be (true)
    }

    scenario("no anagram"){
      val word1 = "rots"

      When("another word is given")
      val word2 = "boss"

      Then("the result is false")
      Anagram().isAnagram(word1,word2) must be (false)
    }

    scenario("my first anagram") {
      Given("a word")
      val word1 = "rots"

      When("an anagram is given")
      val word2 = "sort"

      Then("the result is true")
      Anagram().isAnagram(word1,word2) must be (true)
    }

    scenario("one word included in another"){
      Given("A long word")
      val word1 = "sort"

      When("another word is part of first word")
      val word2 = "rot"

      Then("the result is false")
      Anagram().isAnagram(word1,word2) must be (false)
    }
  }

}
