package com.epam.scala.config

import org.apache.spark.SparkContext
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class UserConfig(val sc: SparkContext) {
  @Bean
  def trashList(): List[String] = {
    val list = sc.textFile("src/songs/trash.txt").take(20)
      .flatMap(s => s.split(", "))
      .toList
    list
  }
}