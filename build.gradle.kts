// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Config.PluginLibs.KOTLIN_GRADLE_PLUGIN)
        classpath(Config.PluginLibs.GRADLE)
        classpath(Config.PluginLibs.HILT_GRADLE_PLUGIN)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}