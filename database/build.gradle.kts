plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
}

android {
    compileSdk = 33
    namespace = "ru.sr.nineteen.database"
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
    implementation(project(":core"))
    implementation(project(":game_engine"))

    implementation ("androidx.room:room-runtime:2.5.1")
    implementation ("androidx.room:room-ktx:2.5.1")
    kapt ("androidx.room:room-compiler:2.5.1")
    implementation ("com.google.code.gson:gson:2.10.1")

    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation (platform("com.google.firebase:firebase-bom:32.0.0"))
    implementation ("com.google.firebase:firebase-database-ktx")
    implementation ("com.google.firebase:firebase-storage-ktx")
    implementation ("io.insert-koin:koin-android:3.4.0")

}