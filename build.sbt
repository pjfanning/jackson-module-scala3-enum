import sbt._
import Keys._
import sbtrelease.ReleasePlugin._

lazy val jacksonModuleScala3Enum = (project in file("."))
  .settings(
    name := "jackson-module-scala3-enum",
    organization := "com.github.pjfanning",
    scalaVersion := "3.0.0-RC3",

    sbtPlugin := false,

    scalacOptions ++= Seq("-deprecation", "-Xcheckinit", "-encoding", "utf8", "-g:vars", "-unchecked", "-optimize"),
    parallelExecution := true,
    homepage := Some(new java.net.URL("https://github.com/pjfanning/jackson-module-scala3-enum/")),
    description := "A library for serializing/deserializing scala3 enums using Jackson.",

    publishTo := Some(
      if (isSnapshot.value)
        Opts.resolver.sonatypeSnapshots
      else
        Opts.resolver.sonatypeStaging
    ),

    licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),

    scmInfo := Some(
      ScmInfo(
        url("https://github.com/pjfanning/jackson-module-scala3-enum"),
        "scm:git@github.com:pjfanning/jackson-module-scala3-enum.git"
      )
    ),

    developers := List(
      Developer(id="pjfanning", name="PJ Fanning", email="", url=url("https://github.com/pjfanning"))
    ),

    libraryDependencies ++= Seq(
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.12.3",
      "org.scalatest" %% "scalatest" % "3.2.8" % Test
    ),

    // enable publishing the main API jar
    packageDoc / publishArtifact := true,

    // build.properties
    Compile / resourceGenerators += Def.task {
      val file = (Compile / resourceManaged).value / "com" / "github" / "pjfanning" / "enum" / "build.properties"
      val contents = "version=%s\ngroupId=%s\nartifactId=%s\n".format(version.value, organization.value, name.value)
      IO.write(file, contents)
      Seq(file)
    }.taskValue
  )

