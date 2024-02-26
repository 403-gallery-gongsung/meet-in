rootProject.name = "meet-in"

include(":application")

include("company")
include("company:model")
include("company:repository")
include("company:api")
include("company:use-case")
include("company:service")
include("company:persist")
include("company:internal-api")
include("company:internal-api-client")

include("user:api")
include("user:internal-api")
include("user:repository")
include("user:internal-api-client")
include("user:service")
include("user:model")
include("user:use-case")
include("user:persist")

include("user:jwtdemo") // will remove

include("common")

pluginManagement {
    buildscript {
        repositories {
            mavenCentral()
            gradlePluginPortal()
        }
    }

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
