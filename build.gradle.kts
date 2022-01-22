import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.properties[key].toString()

plugins {
    // Java support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij") version "1.3.0"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    mavenCentral()
}

intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))

    // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
}

// allprojects {
//     test {
//         useTestNG()
//     }
// }

tasks {
    properties("javaVersion").let {
        withType<JavaCompile> {
            sourceCompatibility = it
            targetCompatibility = it
        }
        // withType<KotlinCompile> {
        //     kotlinOptions.jvmTarget = it
        // }
    }

    wrapper {
        gradleVersion = properties("gradleVersion")
    }
}

// dependencies {
//     compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
//     testCompile "org.testng:testng:6.8.5"
// }

