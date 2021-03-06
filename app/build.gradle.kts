plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("org.jlleitschuh.gradle.ktlint")
    id("kotlin-parcelize")
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
    implementation(Config.AndroidXLibs.CONSTRAINT_LAYOUT)
    implementation(Config.AndroidXLibs.MATERIAL)
    implementation(Config.AndroidXLibs.APP_COMPAT)
    implementation(Config.AndroidXLibs.ANDROIDX_CORE)
    implementation(Config.AndroidXLibs.VIEW_MODEL)
    implementation(Config.AndroidXLibs.LIFECYCLE)
    implementation(Config.AndroidXLibs.FRAGMENT_KTX)
    implementation(Config.AndroidXLibs.NAV_COMPONENT)
    implementation(Config.AndroidXLibs.NAV_COMPONENT_UI)
    implementation(Config.AndroidXLibs.SWIPE_REFRESH)
    implementation(Config.ToolLibs.RETROFIT)
    implementation(Config.ToolLibs.ANDROID_COROUTINES)
    implementation(Config.ToolLibs.EXO_PLAYER)
    implementation(Config.ToolLibs.GLIDE)
    implementation(Config.ToolLibs.RETROFIT_CONVERTER)
    debugImplementation(Config.ToolLibs.LEAK_CANARY)
    implementation(Config.ToolLibs.TIMBER)
    annotationProcessor(Config.ToolLibs.GLIDE_PROCESSOR)
    implementation(Config.DI.HILT)
    kapt(Config.DI.HILT_COMPILER)
    testImplementation(Config.TestLibs.JUNIT)
    testImplementation(Config.TestLibs.COROUTINES_TEST)
    testImplementation(Config.TestLibs.MOCKK)
    androidTestImplementation(Config.TestLibs.ANDROIDX_TEST_EXT)
    androidTestImplementation(Config.TestLibs.ESPRESSO_CORE)
}
kapt {
    correctErrorTypes = true
}
