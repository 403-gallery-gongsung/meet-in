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
include("user:api")
include("user:internal-api")
include("user:repository")
include("user:internal-api-client")
include("user:service")
include("user:model")
include("user:use-case")
include("user:jwtdemo")
findProject(":user:jwtdemo")?.name = "jwtdemo"
