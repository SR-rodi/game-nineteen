plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
    id ("androidx.navigation.safeargs.kotlin")
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
    compileOptions   {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility  = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose  = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }

}

dependencies {

   implementation(project(":core"))
    implementation(project(":core_ui"))
    implementation(project(":navigation"))
    implementation(project(":database"))
    implementation(project(":feature_menu"))

    implementation("io.github.alexgladkov:odyssey-core:1.3.1")
    implementation("io.github.alexgladkov:odyssey-compose:1.3.1")

    implementation ("com.google.code.gson:gson:2.10.1")

    implementation ("androidx.room:room-runtime:2.5.1")
    implementation ("androidx.room:room-ktx:2.5.1")
    kapt ("androidx.room:room-compiler:2.5.1")

    implementation ("io.insert-koin:koin-android:3.4.0")

    implementation( "androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.fragment:fragment-ktx:1.5.7")


    implementation ("androidx.core:core-ktx:1.10.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.activity:activity-compose:1.7.1")
    implementation( platform("androidx.compose:compose-bom:2022.10.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3")
}