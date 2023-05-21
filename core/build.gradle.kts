plugins {
    id ("com.android.library")
    id ("kotlin-parcelize")
    id ("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "ru.sr.nineteen.core"
    defaultConfig {
        minSdk = 29
    }


    compileOptions   {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility  = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

}

dependencies {


    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("io.insert-koin:koin-core:3.4.0")
    implementation ("io.insert-koin:koin-androidx-compose:3.4.0")
    implementation ("io.insert-koin:koin-android:3.4.0")
}