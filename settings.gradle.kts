rootProject.name = "meet-in"

include(":application")

include("common:exception")

include("hello-world:api")
include("hello-world:internal-api")
include("hello-world:internal-api-client")
include("hello-world:model")
include("hello-world:repository")
include("hello-world:persist")
include("hello-world:service")
include("hello-world:use-case")

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

include("user:application")

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
include("feed")
include("feed:model")
include("feed:internal-api")
include("feed:service")
include("feed:use-case")
include("feed:repository")
include("feed:persist")