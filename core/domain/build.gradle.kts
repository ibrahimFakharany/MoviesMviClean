plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.ibrahim.data"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}

dependencies {
    implementation(project(":base"))
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)
}