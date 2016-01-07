name := "inject"

version := "1.03"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

javacOptions  ++= Seq("-target", "1.7", "-source", "1.7", "-Xlint:deprecation")

exportJars := true

sources in (Compile, doc) := Seq.empty

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
)

bintrayRepository := "injectio"

bintrayPackage := "io.inject"

bintrayOrganization := Some("injectio")

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
