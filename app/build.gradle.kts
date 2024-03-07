plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id(libs.plugins.jetbrains.kotlin.kapt.get().pluginId)
    id(libs.plugins.dagger.hilt.get().pluginId)
}

android {
    namespace = "com.kingmodest.testproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kingmodest.testproject"
        minSdk = 27
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
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(libs.material)
    implementation(libs.coroutines)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.moshi)
    implementation(libs.moshi.adapters)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.junit.ktx)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.accompanist)
    implementation(libs.androidx.navigation)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation)

    implementation(libs.preferences)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines)
    testImplementation(libs.mockk)
    testImplementation(libs.kotest.assertions)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}