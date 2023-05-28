plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.ibrahim.domain"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}

dependencies {
    implementation(project(":base"))
    implementation(project(":core:domain"))
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)
    testImplementation("com.squareup.okhttp3:mockwebserver:4.7.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.3.3")
    testImplementation("io.mockk:mockk:1.13.5")

}