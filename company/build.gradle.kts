plugins {
    val kotlinVersion = "1.9.21"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

dependencies {
    implementation(project(":company:internal-api"))
    implementation(project(":company:service"))
    implementation(project(":company:repository"))
}
