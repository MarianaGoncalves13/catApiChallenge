import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")

if(localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

android {
    namespace = "com.mariana.catapichallenge"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mariana.catapichallenge"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"api.thecatapi.com\"")
        buildConfigField("String", "BASE_URL", "\"https://api.thecatapi.com\"")
        buildConfigField("String", "API_KEY", "${localProperties["API_KEY"]}")
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
        buildConfig = true
    }
}

dependencies {

    //Core
    implementation(libs.ksp.plugin)
    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt)
    implementation(libs.androidx.runtime.livedata)
    ksp(libs.hilt.compiler)
    implementation(libs.kotlinx.serialization.json)

    //UI
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.paging)
    implementation(libs.coil)

    //Local
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    //Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization.converter)
    implementation(libs.ok.http)

    //Paging
    implementation(libs.androidx.paging.runtime)

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
