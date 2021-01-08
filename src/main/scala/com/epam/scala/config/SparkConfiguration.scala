package com.epam.scala.config


import com.fasterxml.jackson.databind.introspect.{AnnotationIntrospectorPair, JacksonAnnotationIntrospector}
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, OptionModule, ScalaObjectMapper, TupleModule}
import org.apache.spark.{SparkConf, SparkContext}
import org.codehaus.jackson.map.ObjectMapper
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class SparkConfiguration {
@Bean
  def sparkConfig(): SparkContext ={
  val conf =new SparkConf().setAppName("hi").setMaster("local[*]")
   new SparkContext(conf)

}

}
