rootProject.name = "meet-in"

include(":application")

include("hello-world:api")
include("hello-world:internal-api")
include("hello-world:internal-api-client")
include("hello-world:model")
include("hello-world:repository")
include("hello-world:persist")
include("hello-world:service")
include("hello-world:use-case")

include("auth:api")
include("auth:internal-api-client")
include("auth:model")
include("auth:service")
include("auth:persist")
include("auth:repository")
include("auth:use-case")

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

