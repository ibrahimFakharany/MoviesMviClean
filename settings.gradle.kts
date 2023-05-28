pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = uri("https://kotlin.bintray.com/kotlinx"))
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://kotlin.bintray.com/kotlinx"))
    }
}
rootProject.name = "MoviesCleanMvi"
include(":app")
include(":core")
include(":core:data")
include(":core:domain")
include(":core:remoteDataSource")
include(":feature")
include(":feature:home")
include(":base")
include(":feature:movieDetails")
include(":feature:search")
