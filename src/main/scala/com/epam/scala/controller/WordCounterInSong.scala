package com.epam.scala.controller
import com.epam.scala.config.UserConfig
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s.DefaultFormats
import org.json4s.jackson.Json
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestParam, RestController}
/*
count words in song in descending order and exclude  words from a trash list
 */
@RestController
class WordCounterInSong(val sc: SparkContext, val list: UserConfig) {

  @GetMapping(value=Array("/dest/{word}/{name}"))
  def wordCounting(@PathVariable word: Int,@PathVariable name: String):Any={


    val foo= sc.textFile("src/songs/halo.txt")
      .take(100)
      .flatMap(s=>s.split(" "))           //split by space
      .map(_.toLowerCase)                        //convert to lowerCase
      .filterNot(s=>list.trashList().contains(s))//check if word contains in forbidden list
      .groupBy(identity)                         // group By identity
      .mapValues(_.size)                         // count how many entries each identity
      .toSeq.sortWith(_._2>_._2)                 // sort in descending order by value(entries in the stream)
      .take(word)                                // take n(word) values
    Json(DefaultFormats).write(foo)              // to json format to see a list in localhost

  }


}
