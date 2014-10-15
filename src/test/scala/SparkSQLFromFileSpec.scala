import commons._

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._

import org.specs2._

// you can use custom classes that implement the Product interface.
case class Person(name: String, age: Int)

class SparkSQLFromFileSpec extends Specification with Stoppable { def is = s2"""

  Spark SQL From File

  FromFile
    read from file                          $readFromFile
  """

  var retReadFromFile: Array[String] = _
  using(new SparkContext("local[1]", "SparkSQLFromFileSpec", System.getenv("SPARK_HOME"))) { sc =>
    val sqlContext = new SQLContext(sc)
    import sqlContext.createSchemaRDD

    // Create an RDD of Person objects and register it as a table.
    val people = sc.textFile("src/test/resources/people.txt").map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt))
    people.registerTempTable("people")

    // SQL statements can be run by using the sql methods provided by sqlContext.
    val teenagers = sqlContext.sql("SELECT name, age FROM people WHERE age >= 13 AND age <= 19")

    // The results of SQL queries are SchemaRDDs and support all the normal RDD operations.
    // The columns of a row in the result can be accessed by ordinal.
    retReadFromFile = teenagers.map(t => "%s:%d".format(t(0), t(1))).collect
  }
  def readFromFile = retReadFromFile must_== Array("Justin:19")
}

