Spark SQL サンプルプログラム
-------------------------------


1) simpleapp2.zip を適当な場所に解凍し、そのディレクトリに cd

2) src/main/scala/SimpleApp.scala 内の以下の文字列を適切な文字列に書き換える

YourSparkHomeDir

上記の文字列を spark 1.0.0 のホームディレクトリに書き換える

例) /opt/spark-1.0.0


3) sbt package にてコンパイル、jarファイルの作成

4) 以下にて実行


cd <spark 1.0.0 のホームディレクトリ>

./bin/spark-submit --class "SimpleApp" \
--master local[4] \
target/scala-2.10/simple-project_2.10-1.0.jar
