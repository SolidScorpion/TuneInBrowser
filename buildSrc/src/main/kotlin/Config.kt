object Config {
    object Versions {
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
    }

    object PluginLibs {
        const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE_VERSION}"
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}"
        const val HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_VERSION}"
    }

    object AndroidXLibs {
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
        const val ANDROIDX_CORE = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_VERSION}"
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
