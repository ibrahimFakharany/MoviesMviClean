plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    kotlin("android")
}

android {
    namespace = "com.ibrahim.movies_clean_mvi"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.ibrahim.movies_clean_mvi"
        versionCode = 1
        versionName = "1.0"
        minSdk = 24
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    implementation(project(":feature:home"))
    implementation(project(":feature:movieDetails"))
    implementation(project(":feature:search"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:remoteDataSource"))
    implementation(project(":base"))
    implementation(libs.multidex)
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

    implementation(libs.core.splashscreen)

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)

}