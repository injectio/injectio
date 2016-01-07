name := "injectio"

version := "1.03"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

javacOptions  ++= Seq("-target", "1.7", "-source", "1.7", "-Xlint:deprecation")

exportJars := true

resolvers += Resolver.sonatypeRepo("public")

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
)