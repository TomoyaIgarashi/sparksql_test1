name := "Simple Project"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.0.0" % "provided" // Spark-CORE: do not forget %% to select spark-core distribution reflecting Scala version

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.0.0" % "provided" // Spark-SQL

