plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.ibrahim.base"
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
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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

    implementation(libs.androidx.core.ktx)

    implementation(libs.bundles.coroutines)

    implementation(libs.bundles.lifecycle)
    implementation(libs.coil)
    implementation(libs.coilCompose)
    implementation(libs.android.browser)

    implementation(libs.bundles.navigation)
    implementation(libs.bundles.retrofit)
    kapt(libs.moshi.kotlin.codegen)
    implementation(libs.bundles.compose)

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)
}