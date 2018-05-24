name := "book_lib"

version := "0.1"

scalaVersion := "2.12.6"

// Scala 2.10, 2.11, 2.12
libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"       % "3.2.2",
  "com.h2database"  %  "h2"                % "1.4.197",
  "org.hsqldb"         % "hsqldb"               % "2.4.0",
  "ch.qos.logback"  %  "logback-classic"   % "1.2.3",
  "com.typesafe" % "config" % "1.2.1"
)