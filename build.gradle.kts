// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        repositories {
            maven("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath(Config.PluginLibs.KOTLIN_GRADLE_PLUGIN)
        classpath(Config.PluginLibs.GRADLE)
        classpath(Config.PluginLibs.HILT_GRADLE_PLUGIN)
        classpath(Config.PluginLibs.KTLINT)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}