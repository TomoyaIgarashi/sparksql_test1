name := "Spark SQL examples"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature", "-Xelide-below", "ALL")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.1.0",
  "org.apache.spark" %% "spark-sql" % "1.1.0",
  "org.specs2" %% "specs2" % "2.4.1"
)

