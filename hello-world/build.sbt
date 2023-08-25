val scala3Version = "3.3.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "hello-world",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test

    // added AKKA dependencies 
    // based on the example "Sending HTTP Requests in Scala and Akka in 5 minutes"
    //  https://blog.rockthejvm.com/a-5-minute-akka-http-client/
    libraryDependencies ++= Seq(
      // akka streams
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      // akka http
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    )
  )
