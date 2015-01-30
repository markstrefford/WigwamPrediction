//import AssemblyKeys._

//seq(assemblySettings: _*)

name := "WigWam Prediction App"

// http://dl.bintray.com/sbt/sbt-plugin-releases/com.eed3si9n/sbt-assembly/scala_2.10/
//resolvers += "Bintray sbt plugin releases" at "http://dl.bintray.com/sbt/sbt-plugin-releases/"

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.12.0")

resolvers += "sbt-assembly-resolver-0" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases"

libraryDependencies ++= Seq(
  "commons-lang" % "commons-lang" % "2.6",
  "org.apache.spark" %% "spark-core" % "1.1.1",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.1.1",
  "com.datastax.cassandra" % "cassandra-driver-core" % "1.2.0-cdh5.3.0"
)

//libraryDependencies += "com.eed3si9n" % "sbt-assembly" % "0.7.2"
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.7.2")

//traceLevel in run := 0

//fork in run := true

//scalacOptions ++= Seq("-optimize")

// The following is the class that will run when the jar is compiled!

//mainClass in assembly := Some("MyMain")