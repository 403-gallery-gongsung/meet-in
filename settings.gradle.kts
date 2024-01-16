rootProject.name = "meet-in"

include(":application")

include("hello-world:api")
include("hello-world:internal-api")
include("hello-world:internal-api-client")
include("hello-world:model")
include("hello-world:repository")
include("hello-world:service")
include("hello-world:use-case")

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
include("company")
include("company:model")
include("company:repository")
include("company:api")
include("company:use-case")
include("company:service")
