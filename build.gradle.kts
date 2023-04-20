plugins {
    kotlin("jvm") version "1.7.20"
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

group = "com.timoliacreative"
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
        url = uri("https://git.timoliacreative.de/api/v4/projects/32/packages/maven")
        authTcGitlab()
    }
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
    implementation("com.timoliacreative:tranclate:2.9-dev11")
    implementation("com.timoliacreative:tranclate-std-lib:0.9.1-dev1")

    //test
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}