plugins {
    kotlin("jvm") version "2.1.20"
    id("application")
}

group = "com.lotsofpixelsstudios"
version = "1"


repositories {
    mavenCentral()
}

application {
    mainClass.set("com.tcreative.addons.MainKt")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.lotsofpixelsstudios:monstera:0.5.7")
    implementation("com.lotsofpixelsstudios:monstera-stdlib:0.11.2")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.18")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.17")

    //test
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}