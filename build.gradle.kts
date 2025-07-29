plugins {
    kotlin("jvm") version "2.2.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("tech.units:indriya:2.1")
}

application {
    mainClass.set("com.example.MainKt") // your main class fully qualified name
}
