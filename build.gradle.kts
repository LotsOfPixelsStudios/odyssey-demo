plugins {
    kotlin("jvm") version "1.9.20"
    id("application")
}

//load local env file
val localEnvFile = File(
    "${System.getProperties().getProperty("user.home")}${File.separator}.gradle",
    "env-timoliacreative.local.gradle.kts"
)
if (localEnvFile.exists()) {
    //set project extras
    apply(from = localEnvFile.path)
} else {
    //gitlab project that has access to the repo
    project.extra.set("gitlab_token", System.getenv("CI_JOB_TOKEN") as String)
}

group = "com.lotsofpixelsstudios"
version = "1"

fun MavenArtifactRepository.authTcGitlab() {
    credentials(HttpHeaderCredentials::class) {
        name = if (localEnvFile.exists()) "Private-Token" else "Job-Token"
        value = project.extra["gitlab_token"] as String
    }
    authentication {
        create<HttpHeaderAuthentication>("header")
    }
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://git.timoliacreative.de/api/v4/projects/102/packages/maven")
        authTcGitlab()
    }
}

application {
    mainClass.set("com.tcreative.addons.MainKt")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.lotsofpixelsstudios:monstera:0.2.4")
    implementation("com.lotsofpixelsstudios:monstera-std-lib:0.10-monstera-2")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.4.11")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.9")

    //test
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}