scalaVersion := "2.12.10"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "ch.epfl.data" %% "squid" % "0.4.0-SNAPSHOT"

lazy val docs = project // documentation project
  .in(file("mdoc"))
  // .dependsOn(root)
  .enablePlugins(MdocPlugin)
  .settings(
    resolvers += Resolver.sonatypeRepo("snapshots"),
    libraryDependencies += "ch.epfl.data" %% "squid" % "0.4.0-SNAPSHOT",
  )
