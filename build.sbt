sbtPlugin := true

name := "sbt-native-packager"

organization := "com.gettyimages.sbt"

scalacOptions in Compile += "-deprecation"

libraryDependencies += "org.apache.commons" % "commons-compress" % "1.4.1"

site.settings

com.typesafe.sbt.SbtSite.SiteKeys.siteMappings <+= (baseDirectory) map { dir => 
  val nojekyll = dir / "src" / "site" / ".nojekyll"
  nojekyll -> ".nojekyll"
}

site.sphinxSupport()

ghpages.settings

git.remoteRepo := "git@gitlab.amer.gettywan.com:digitalassetmanagement/sbt-native-packager.git"

credentials += Credentials("Sonatype Nexus Repository Manager",
                          "sea-ops-artifacts-01.amer.gettywan.com",
                          "deployment",
                          "x69kg1zM8cd9")

publishTo <<= version { (v: String) =>
  val nexusBaseUrl = "http://sea-ops-artifacts-01.amer.gettywan.com:8081/nexus/content/repositories/"
  if(v.trim.endsWith("SNAPSHOT")) {
    Some("getty-snapshots-publish" at nexusBaseUrl + "/snapshots")
  } else {
    Some("getty-releases-publish" at nexusBaseUrl + "/releases")
  }
}

publishMavenStyle := true

scriptedSettings

scriptedLaunchOpts <+= version apply { v => "-Dproject.version="+v }





