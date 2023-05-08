
plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "ru.sr.nineteen.profile"
    defaultConfig {
        minSdk = 29
    }
    buildFeatures {
        compose = true
    }

    compileOptions   {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility  = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core_ui"))
    implementation(project(":database"))

    implementation("io.github.alexgladkov:odyssey-core:1.3.1")
    implementation("io.github.alexgladkov:odyssey-compose:1.3.1")

    implementation (platform("com.google.firebase:firebase-bom:32.0.0"))
    implementation ("com.google.firebase:firebase-auth-ktx")

    implementation("androidx.activity:activity-compose:1.7.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation ("io.insert-koin:koin-core:3.4.0")
    implementation ("io.insert-koin:koin-androidx-compose:3.4.0")
    implementation ("io.insert-koin:koin-android:3.4.0")
}