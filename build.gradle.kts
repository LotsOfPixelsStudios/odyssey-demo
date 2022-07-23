plugins {
    kotlin("jvm") version "1.7.0"
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
}

group = "com.timoliacreative"
version = "1"

repositories {
    mavenCentral()
    maven {
        url = uri(project.extra["maven_repo_url"] as String)
        name = "Reposilite"

        credentials {
            username = project.extra["maven_repo_user"] as String
            password = project.extra["maven_repo_pw"] as String
        }
        authentication {
            create<BasicAuthentication>("basic")
        }
    }
}

application {
    mainClass.set("com.tcreative.addons.MainKt")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation(group = "com.timoliacreative", name = "tranclate", version = "2.2.5")
    implementation(group = "com.timoliacreative", version = "0.4.3-furniture-snapchot", name = "tranclate-std-lib") {
        isChanging = true
    }

    //test
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}