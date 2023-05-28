plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.ibrahim.remotedatasource"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
    }

}

dependencies {
    implementation(project(":base"))
    implementation(project(":core:data"))
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.retrofit)
    kapt(libs.hilt.android.compiler)
}