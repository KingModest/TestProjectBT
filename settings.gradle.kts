pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        resolutionStrategy {
            eachPlugin {
                if (requested.id.id == "dagger.hilt.android.plugin") {
                    useModule("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
                }
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TestProjectBT"
include(":app")
 