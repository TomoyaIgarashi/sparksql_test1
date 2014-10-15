import commons._

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._

import org.specs2._

class SparkSQLFromJSONSpec extends Specification with Stoppable { def is = s2"""

  SparkSQL specification

  From JSON
    read from json                         $readFromJSON

  """

  val json = Seq(
    """{"name":"Yin","address":{"city":"Columbus","state":"Ohio"},"datetime":"2014-08-12 19:07:43"}""",
    """{"name":"Mary","address":{"city":"Manhattan","state":"NewYork"},"datetime":"2014-08-14 19:07:43"}""",
    """{"name":"Mike","address":{"city":"Los Angeles","state":"California"},"datetime":"2014-08-11 19:07:43"}"""
  )
  var retReadFromJSON: Seq[String] = _
  using(new SparkContext("local[1]", "SparkSQLFromJSONSpec", System.getenv("SPARK_HOME"))) { sc =>
    val sqlContext = new SQLContext(sc)
    val rdd = sc.parallelize(json)
    val rddSql = sqlContext.jsonRDD(rdd)
    rddSql.registerTempTable("people")
    val ret = sqlContext.sql(
      "SELECT name, address.city  FROM people WHERE name IN ('Yin', 'Mike') AND datetime < '2014-08-14 00:00:00'")
    retReadFromJSON = ret.collect.map { row =>
      "%s, %s".format(row.getString(0), row.getString(1))
    }
  }
  def readFromJSON = retReadFromJSON must_== Seq(
    "Yin, Columbus",
    "Mike, Los Angeles"
  )
}
