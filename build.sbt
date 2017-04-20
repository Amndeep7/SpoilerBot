name := "SpoilerBot"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies += "com.github.austinv11" % "Discord4J" % "dev-SNAPSHOT"

resolvers ++= Seq(
  "jitpack" at "https://jitpack.io",
  "jcenter" at "https://jcenter.bintray.com"
)