Basic Spark wordcount app from http://blog.cloudera.com/blog/2014/04/how-to-run-a-simple-apache-spark-app-in-cdh-5/

Note this is using Gradle instead of maven!

To run: 

spark-submit --class com.cloudera.sparkwordcount.SparkWordCount --master local build/libs/WigwamPrediction.jar data/sparkwordcount/input.txt 2 
