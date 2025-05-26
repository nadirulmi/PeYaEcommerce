plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.peyaecommerce"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.peyaecommerce"
        minSdk = 27
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Kotlin Stdlib y Core KTX
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core.ktx)

    // UI
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.recyclerview)
    implementation("androidx.fragment:fragment:1.8.7")

    // Hilt (inyección de dependencias)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Retrofit con Gson
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // Room (base de datos local)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // ViewModel y LiveData (Lifecycle)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    // Navigation (fragment y ui)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Glide (carga de imágenes)
    implementation(libs.glide)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}