import java.util.Properties

plugins {
    id("com.google.dagger.hilt.android") version "2.56.2"
    kotlin("kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
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

        buildConfigField(
            "String",
            "CLOUDINARY_CLOUD_NAME",
            "\"${localProperties["CLOUDINARY_CLOUD_NAME"]}\""
        )

        buildConfigField(
            "String",
            "CLOUDINARY_API_KEY",
            "\"${localProperties["CLOUDINARY_API_KEY"]}\""
        )

        buildConfigField(
            "String",
            "CLOUDINARY_API_SECRET",
            "\"${localProperties["CLOUDINARY_API_SECRET"]}\""
        )
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
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.6.4"
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
    implementation("androidx.cardview:cardview:1.0.0")

    implementation(libs.androidx.material3.android)

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

    // Compose UI
    implementation("androidx.compose.ui:ui:1.6.4")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.4")

    // Activity para Compose
        implementation("androidx.activity:activity-compose:1.9.0")

    // Lifecycle ViewModel + Compose
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Optional: Para previsualizaciones y tooling
        debugImplementation("androidx.compose.ui:ui-tooling:1.6.4")

    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.56.2")
    kapt("com.google.dagger:hilt-android-compiler:2.56.2")

    //Cloudinary
    implementation("com.cloudinary:cloudinary-android:2.3.1")

    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")

    //Lottie for animations
    implementation("com.airbnb.android:lottie-compose:6.4.0")

    //Room
    implementation(libs.room.runtime)
    kapt("androidx.room:room-compiler:2.7.2")

    //Test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // OkHttp Logging
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

}