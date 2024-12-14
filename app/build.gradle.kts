plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //alias(libs.plugins.serialization)
}

android {
    namespace = "com.example.taskcomposeexample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.taskcomposeexample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.runtime.livedata)
    // testing
    testImplementation(libs.mockk)
    testImplementation(libs.androidx.core.testing)

    // 📦 Dependencia necesaria
    implementation("androidx.compose.material3:material3-window-size-class:1.3.1")
    // Jetpack Compose integration
    implementation(libs.androidx.navigation.compose)
    // Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)
    // serialization
  //  implementation(libs.kotlinx.serialization.json)

    // Test rules and transitive dependencies:
    //androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
// Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
   // debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.6")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$rootProject.composeVersion")
}