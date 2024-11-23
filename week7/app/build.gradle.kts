plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "1.9.0-1.0.11"
}

android {
<<<<<<<< HEAD:week7/app/build.gradle.kts
    namespace = "com.example.flo_clone"
    compileSdk = 34
========
    viewBinding {
        enable = true
    }
    namespace = "com.example.umc_week5"
    compileSdk = 35
>>>>>>>> main:week5/app/build.gradle.kts

    defaultConfig {
        applicationId = "com.example.flo_clone"
        minSdk = 21
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
        viewBinding = true
<<<<<<<< HEAD:week7/app/build.gradle.kts
========
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
>>>>>>>> main:week5/app/build.gradle.kts
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
<<<<<<<< HEAD:week7/app/build.gradle.kts
========
    implementation(libs.androidx.lifecycle.runtime.ktx)
>>>>>>>> main:week5/app/build.gradle.kts
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.gson.v2101)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
<<<<<<<< HEAD:week7/app/build.gradle.kts

    // RoomDB
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
========
>>>>>>>> main:week5/app/build.gradle.kts
}