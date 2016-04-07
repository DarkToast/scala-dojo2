package anagram.christian

import org.scalatest.{FeatureSpec, GivenWhenThen, MustMatchers}

class AnagramSpec extends FeatureSpec with GivenWhenThen with MustMatchers {

  feature("Find some anagrams") {
    scenario("Simple anagram") {
      val expect = Result(List(List("kinship", "pinkish"), List("sinks", "skins")), Set("kinship", "pinkish"), Set("kinship", "pinkish"))

      Anagram.find("kinship" :: "pinkish" :: "sinks" :: "skins" :: Nil) mustBe expect
    }

    scenario("No anagrams") {
      val expect = Result(List(), Set(), Set())

      Anagram.find("kinship" :: "sinks" :: Nil) mustBe expect
    }
  }
}
