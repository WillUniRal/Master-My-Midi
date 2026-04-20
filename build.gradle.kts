
plugins {
    java
    application
    id("org.javamodularity.moduleplugin") version "1.8.15"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "2.25.0"
}

group = "uk.ac.bucks.willralph"
version = "0.1"

repositories {
    mavenCentral()

}

val junitVersion = "5.12.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainModule.set("uk.ac.bucks.willralph.mmmidi")
    mainClass.set("uk.ac.bucks.willralph.mmmidi.HelloApplication")
}

javafx {
    version = "21.0.6"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web", "javafx.swing")
}

dependencies {
    implementation("org.controlsfx:controlsfx:11.2.1")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    // Source: https://mvnrepository.com/artifact/com.gluonhq/charm-glisten
    // implementation("com.gluonhq:charm-glisten:5.0.1")
    implementation("eu.hansolo:tilesfx:21.0.9") {
        exclude(group = "org.openjfx")
    }
    // only needed for scaling, proper implementation should use a thread sleeping until a delay
    implementation("com.github.kwhat:jnativehook:2.2.2")

    // Source: https://mvnrepository.com/artifact/com.fazecast/jSerialComm
    implementation("com.fazecast:jSerialComm:2.11.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jlink {
    imageZip.set(layout.buildDirectory.file("/distributions/app-${javafx.platform.classifier}.zip"))
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
    launcher {
        name = "app"
    }
}
//
// jar --create --file app.jar --manifest MANIFEST.MF -C classes . -C src/main/resources .
tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "uk/ac/bucks/willralph/mmmidi/Launcher.class"

    }
}
tasks.register<Copy>( "copyDependencies") {
    from(configurations.runtimeClasspath)
    into("lib")
}
