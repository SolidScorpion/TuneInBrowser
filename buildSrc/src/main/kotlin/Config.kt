object Config {
    object Versions {
        const val COROUTINE_VERSION = "1.5.1"
        const val MINSDK = 24
        const val TARGET_SDK = 30
        const val COMPILE_SDK = 30
        const val JUNIT_VERSION = "4.13.2"
        const val CONSTRAINT_LAYOUT_VERSION = "2.1.0"
        const val MATERIAL_VERSION = "1.4.0"
        const val APP_COMPAT_VERSION = "1.3.1"
        const val ANDROIDX_CORE_VERSION = "1.6.0"
        const val TEST_EXT_VERSION = "1.1.3"
        const val ESPRESSO_VERSION = "3.4.0"
        const val GRADLE_VERSION = "7.0.2"
        const val KOTLIN_VERSION = "1.5.30"
        const val HILT_VERSION = "2.38.1"
        const val RETROFIT_VERSION = "2.9.0"
        const val EXO_PLAYER_VERSION = "2.15.0"
        const val LIFECYCLE_VERSION = "2.4.0-beta01"
        const val GLIDE_VERSION = "4.12.0"
        const val NAV_COMPONENT_VERSION = "2.3.5"
    }

    object PluginLibs {
        const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE_VERSION}"
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}"
        const val HILT_GRADLE_PLUGIN =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_VERSION}"
    }

    object AndroidXLibs {
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
        const val ANDROIDX_CORE = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_VERSION}"
        const val VIEW_MODEL =
            "androidx.lifecycle:lifecycle-viewmodel:${Versions.LIFECYCLE_VERSION}"
        const val NAV_COMPONENT =
            "androidx.navigation:navigation-fragment:${Versions.NAV_COMPONENT_VERSION}"
        const val NAV_COMPONENT_UI =
            "androidx.navigation:navigation-ui:${Versions.NAV_COMPONENT_VERSION}"
    }

    object ToolLibs {
        const val ANDROID_COROUTINES =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE_VERSION}"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
        const val EXO_PLAYER =
            "com.google.android.exoplayer:exoplayer:${Versions.EXO_PLAYER_VERSION}"
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}"
        const val GLIDE_PROCESSOR = "com.github.bumptech.glide:compiler:${Versions.GLIDE_VERSION}"
    }

    object DI {
        const val HILT = "com.google.dagger:hilt-android:${Versions.HILT_VERSION}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT_VERSION}"
    }

    object TestLibs {
        const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
        const val ANDROIDX_TEST_EXT = "androidx.test.ext:junit:${Versions.TEST_EXT_VERSION}"
        const val ESPRESSO_CORE =
            "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"
    }
}
