import com.linecorp.support.project.multi.recipe.configureByTypeHaving
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    base
    jacoco
    `jvm-test-suite`
    `jacoco-report-aggregation`
    java
    `java-library`
    publishing

    id("org.springframework.boot") version "3.2.1" apply false
    id("io.spring.dependency-management") version "1.1.4"

    id("com.linecorp.build-recipe-plugin") version "1.1.1"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.0"

    val kotlinVersion = "1.9.21"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

val kotlinVersion = "1.9.21"
val javaVersion = "17"

allprojects {
    dependencyLocking {
        lockAllConfigurations()
    }


    configurations.all(
        {
            resolutionStrategy.cacheChangingModulesFor(10, TimeUnit.SECONDS)
        },
    )
}

configureByTypeHaving("kotlin") {
    apply(plugin = "java")
    apply(plugin = "jacoco")
    apply(plugin = "jacoco-report-aggregation")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    java {
        sourceCompatibility = JavaVersion.toVersion(javaVersion)
        targetCompatibility = JavaVersion.toVersion(javaVersion)
        withSourcesJar()
    }

    tasks {
        withType<JavaCompile> {
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
        }

        withType<KotlinJvmCompile> {
            kotlinOptions {
                jvmTarget = javaVersion
                freeCompilerArgs =
                    listOf(
                        "-Xjsr305=strict",
                    )
            }
        }

        withType<Test> {
            useJUnitPlatform()
        }

        detekt {
            config.from(rootProject.files("detekt/detekt.yaml"))
        }

        withType<Detekt> {
            jvmTarget = javaVersion
        }
    }

    dependencies {
        implementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))
        implementation(enforcedPlatform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:1.7.1"))

        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib"))

        implementation("io.github.oshai:kotlin-logging-jvm:5.0.1")

        testImplementation(platform(SpringBootPlugin.BOM_COORDINATES))
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    }
}

configureByTypeHaving("lib") {
    apply(plugin = "java-library")
}

configureByTypeHaving("boot") {
    dependencies {
        implementation(platform(SpringBootPlugin.BOM_COORDINATES))
        annotationProcessor(platform(SpringBootPlugin.BOM_COORDINATES))

        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-validation:3.2.1")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    }
}

configureByTypeHaving("kotlin", "boot") {
    apply(plugin = "kotlin-spring")

    dependencies {
        kapt(platform(SpringBootPlugin.BOM_COORDINATES))
        kapt("org.springframework.boot:spring-boot-configuration-processor")
    }
}

configureByTypeHaving("boot", "jpa", "repository") {
    dependencies {
        api("org.springframework.boot:spring-boot-starter-data-jpa")
        testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")

        runtimeOnly("com.mysql:mysql-connector-j")
    }
}

configureByTypeHaving("boot", "jpa", "repository", "querydsl") {
    val queryDslVersion = "5.0.0:jakarta"
    apply(plugin = "kotlin-jpa")

    dependencies {
        implementation("com.querydsl:querydsl-jpa:$queryDslVersion")
        kapt("com.querydsl:querydsl-apt:$queryDslVersion")
        testImplementation("com.querydsl:querydsl-apt:$queryDslVersion")
    }
}

configureByTypeHaving("boot", "mvc") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator:3.2.1")

    }
}

configureByTypeHaving("security") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-validation:3.1.0")
        implementation("org.springframework.boot:spring-boot-starter-security:3.1.0")

        implementation("io.jsonwebtoken:jjwt-api:0.11.5")
        runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
        runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    }
}

configureByTypeHaving("boot", "application") {
    apply(plugin = "org.springframework.boot")

    tasks.withType<BootJar> {
        enabled = false
    }
}

configureByTypeHaving("client") {
    val feignClientVersion = "4.1.0"
    dependencies {
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign:$feignClientVersion")
    }
}
