pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "NineTeen"
include (":app")
include (":core")
include (":navigation")
include (":database")
include (":core_ui")
include (":feature_game")
include (":feature_rating")
include (":feature_training")
include (":feature_menu")

