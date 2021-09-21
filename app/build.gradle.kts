import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Config.Versions.COMPILE_SDK

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.apripachkin.tuneinbrowser"
        minSdk = Config.Versions.MINSDK
        targetSdk = Config.Versions.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-DEV"
            isDebuggable = true
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation (Config.AndroidXLibs.CONSTRAINT_LAYOUT)
    implementation (Config.AndroidXLibs.MATERIAL)
    implementation (Config.AndroidXLibs.APP_COMPAT)
    implementation (Config.AndroidXLibs.ANDROIDX_CORE)
    implementation (Config.AndroidXLibs.VIEW_MODEL)
    implementation (Config.AndroidXLibs.NAV_COMPONENT)
    implementation (Config.AndroidXLibs.NAV_COMPONENT_UI)
    implementation (Config.ToolLibs.RETROFIT)
    implementation (Config.ToolLibs.ANDROID_COROUTINES)
    implementation (Config.ToolLibs.EXO_PLAYER)
    implementation (Config.ToolLibs.GLIDE)
    annotationProcessor(Config.ToolLibs.GLIDE_PROCESSOR)
    implementation (Config.DI.HILT)
    kapt (Config.DI.HILT_COMPILER)
    testImplementation (Config.TestLibs.JUNIT)
    androidTestImplementation (Config.TestLibs.ANDROIDX_TEST_EXT)
    androidTestImplementation (Config.TestLibs.ESPRESSO_CORE)
}
kapt {
    correctErrorTypes = true
}