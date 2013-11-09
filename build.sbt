name := "playthrift"

version := "0.1-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings

libraryDependencies ++= Seq(
  // scrooge
  "com.twitter" %% "scrooge-core" % "3.6.0",
  "org.apache.thrift" % "libthrift" % "0.9.1",
  "com.twitter" %% "finagle-core" % "6.5.0",
  "com.twitter" %% "finagle-thrift" % "6.5.0"
)

com.twitter.scrooge.ScroogeSBT.newSettings

scroogeThriftSourceFolder in Compile := baseDirectory.value / "thrift" 

watchSources <++= baseDirectory map { path => ((path / "thrift") ** "*.thrift").get }

scroogeThriftOutputFolder in Compile := (sourceManaged in Compile).value / "thrift_src_managed"



// enable -feature flag
// enable higherKinds to remove warnings from scrooge-generated code that higher-kinded type should be enabled

scalacOptions ++= Seq("-feature", "-language:higherKinds")

