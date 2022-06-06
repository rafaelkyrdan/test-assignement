import sbt.Keys.libraryDependencies

val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "test-assignement",
    version := "0.1.0-SNAPSHOT",
    scalafmtOnCompile := true,

    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      library.scalaMeta,
      library.zio,
      library.zioTest    % Test,
      library.zioTestSbt % Test
    )
  )

lazy val library =
  new {
    object Version {
      val zio = "1.0.14"
    }
    val zio        = "dev.zio" %% "zio"          % Version.zio
    val zioTest    = "dev.zio" %% "zio-test"     % Version.zio
    val zioTestSbt = "dev.zio" %% "zio-test-sbt" % Version.zio
    val scalaMeta = "org.scalameta" %% "munit" % "0.7.29" % Test
  }
