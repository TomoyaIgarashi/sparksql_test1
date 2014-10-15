name := "Simple Project"

version := "1.0"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-feature", "-Xelide-below", "ALL")

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos"

libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.0.0"

libraryDependencies += "org.apache.spark" % "spark-sql_2.10" % "1.0.0"

