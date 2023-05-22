plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 33
    defaultConfig {
        namespace = "ru.sr.nineteen"
        applicationId = "ru.sr.nineteen"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            //proguardFiles getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }

}

dependencies {

    implementation(project(":game_engine"))
    implementation(project(":core"))
    implementation(project(":core_ui"))
    implementation(project(":nav_graph"))
    implementation(project(":database"))
    implementation(project(":feature_menu"))
    implementation(project(":feature_game"))
    implementation(project(":feature_rating"))
    implementation(project(":feature_authorization"))
    implementation(project(":feature_profile"))
    implementation(project(":feature_training"))

    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")

    implementation ("androidx.core:core-splashscreen:1.0.1")

    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.room:room-runtime:2.5.1")
    implementation("androidx.room:room-ktx:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")

    implementation("io.insert-koin:koin-android:3.4.0")

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.material3:material3")
}