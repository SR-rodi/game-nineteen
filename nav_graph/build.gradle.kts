plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33
    namespace = "ru.sr.nineteen.navgraph"
    defaultConfig {
        minSdk = 24
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

    implementation(project(":game_engine"))


    implementation(project(":core"))
    implementation(project(":core_ui"))
    implementation(project(":feature_menu"))
    implementation(project(":feature_training"))
    implementation(project(":feature_rating"))
    implementation(project(":feature_game"))
    implementation(project(":feature_authorization"))
    implementation(project(":feature_profile"))

    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation("io.github.alexgladkov:odyssey-core:1.3.1")
    implementation("io.github.alexgladkov:odyssey-compose:1.3.1")

    implementation ("androidx.activity:activity-compose:1.7.1")
    implementation( platform("androidx.compose:compose-bom:2022.10.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3")
}