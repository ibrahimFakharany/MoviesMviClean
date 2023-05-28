plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.ibrahim.moviedetails"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {

    implementation(project(":base"))
    implementation(project(":core:domain"))
    implementation(libs.bundles.coroutines)
    implementation(libs.multidex)
    implementation(libs.coil)
    implementation(libs.coilCompose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.lifecycle)
    implementation(libs.androidx.appcompat)
    implementation(libs.moshi.kotlin)
    implementation(libs.bundles.compose)
    androidTestImplementation(libs.test.android.composeUi)
    debugImplementation(libs.bundles.debugCompose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)
}