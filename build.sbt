name := """BusinessAppSample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.play" %% "play-slick" % "0.8.1",
  "org.postgresql" % "postgresql" % "9.4-1200-jdbc41",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.pegdown" % "pegdown" % "1.2.1",
  "com.github.tototoshi" %% "play-json4s-jackson" % "0.3.1",
  "com.github.tototoshi" %% "play-json4s-test-jackson" % "0.3.1" % "test"
)
