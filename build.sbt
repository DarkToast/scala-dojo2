name := "scala-workshop"

version := "0.1.0-SNAPSHOT"

organization := "de.tarent"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-Xmax-classfile-name", "128", "-deprecation","-unchecked")

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.5"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.5"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.3" % "test"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

libraryDependencies += "joda-time" % "joda-time" % "2.9.2"


resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")

lazy val root =
  project.in( file(".") )

