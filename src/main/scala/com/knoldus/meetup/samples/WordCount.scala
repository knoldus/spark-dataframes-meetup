package com.knoldus.meetup.samples

import com.knoldus.meetup.core.CoreSystemApi
import org.apache.spark.sql.DataFrame

class WordCount(coreSystemApi: CoreSystemApi) {
  import coreSystemApi.sqlContext.implicits._

  def get(): DataFrame = {
    val linesDF = coreSystemApi.sqlContext.sparkContext.textFile("src/main/resources/file.txt").toDF("line")
    val wordsDF = linesDF.explode("line", "word")((line: String) => line.split(" "))
    wordsDF.groupBy("word").count()
  }
}