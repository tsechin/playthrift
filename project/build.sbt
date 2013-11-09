scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.8.0",
  "com.twitter" %% "scrooge-core" % "3.6.0",
  "com.twitter" %% "scrooge-generator" % "3.6.0",
  "com.twitter" %% "finagle-thrift" % "6.5.0"
)

