package com.wigwam

//import com.datastax.spark.connector.cql.CassandraConnector
import org.apache.spark.SparkContext
//import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import com.datastax.spark.connector._

/**
 * Created by markstrefford on 13/01/15.
 *
 * Determine the min, average and max travel times as measured by each ANPR point and stored in Cassandra
 */
object TravelEventStats {
  def main(args: Array[String]) {
    val dbHostname: String = args(1)
    val eventType = "ANPR" // args(2)
    System.out.println();

    System.setProperty("spark.cassandra.query.retry.count", "1") // don't retry

    val sc = new SparkContext(
      new SparkConf()
      .setAppName("Spark Travel Event Counter")
      .set("spark.cassandra.connection.host", dbHostname)
      .set("spark.cassandra.auth.username", "cassandra")
      .set("spark.cassandra.auth.password", "cassandra")
    )


    //val sc = new SparkContext("spark://192.168.123.10:7077", "test", conf)

    // select * from travel_time where measurement_site_id = 'ANPR_Measurement_Site_30071605' and date='2014:12:06';

    //    CassandraConnector(conf).withSessionDo { session =>
    //      session.execute("CREATE KEYSPACE test2 WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1 }")
    //      session.execute("CREATE TABLE test2.words (word text PRIMARY KEY, count int)")
    //    }

    //    val rdd = sc.cassandraTable("travel_time_keyspace", "travel_time")
    //    // rdd: com.datastax.spark.connector.rdd.CassandraRDD[com.datastax.spark.connector.rdd.reader.CassandraRow] = CassandraRDD[0] at RDD at CassandraRDD.scala:41
    //
    //    rdd.collect.foreach(println)

    val rdd = sc.cassandraTable("travel_time_keyspace", "travel_time").select("measurement_site_id", "publication_time", "duration").where("measurement_site_id = ?", "ANPR_Measurement_Site_30071362")
    rdd.collect.foreach(println)

  }
}


//
//object SparkWordCount {
//  def main(args: Array[String]) {
//    val sc = new SparkContext(new SparkConf().setAppName("Spark Count"))
//    val threshold = args(1).toInt
//
//    // split each document into words
//    val tokenized = sc.textFile(args(0)).flatMap(_.split(" "))
//
//    // count the occurrence of each word
//    val wordCounts = tokenized.map((_, 1)).reduceByKey(_ + _)
//
//    // filter out words with less than threshold occurrences
//    val filtered = wordCounts.filter(_._2 &gt;= threshold)
//
//    // count characters
//    val charCounts = filtered.flatMap(_._1.toCharArray).map((_, 1)).reduceByKey(_ + _)
//
//    System.out.println(charCounts.collect().mkString(", "))
//  }
//}
