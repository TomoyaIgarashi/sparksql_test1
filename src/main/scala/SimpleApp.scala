/* SimpleApp.scala */
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object SimpleApp {

  // you can use custom classes that implement the Product interface.
  case class Person(name: String, age: Int)


  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)
    import sqlContext._

    // Define the schema using a case class.
    // Note: Case classes in Scala 2.10 can support only up to 22 fields. To work around this limit, 


    // Create an RDD of Person objects and register it as a table.
    val people = sc.textFile("YourSparkHomeDir/examples/src/main/resources/people.txt").map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt))
    people.registerAsTable("people")

    // SQL statements can be run by using the sql methods provided by sqlContext.
    val teenagers = sql("SELECT name FROM people WHERE age >= 13 AND age <= 19")

    // The results of SQL queries are SchemaRDDs and support all the normal RDD operations.
    // The columns of a row in the result can be accessed by ordinal.
    teenagers.map(t => "Name: " + t(0)).collect().foreach(println)
  }
}

