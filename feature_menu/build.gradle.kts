plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "ru.sr.nineteen.menu"
    defaultConfig {
        minSdk = 29
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }

}

dependencies {
}