//apply plugin: 'kotlin-kapt'
//
//apply plugin: "androidx.navigation.safeargs"

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

//signingreport

android {
    namespace = "com.test.gravardados"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.test.gravardados"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

//    buildscript {
//        ext.kotlin_version = "1.3.61"
//        repositories {
//            google()
//            jcenter()
//
//        }
//        dependencies {
//            classpath("com.android.tools.build:gradle:3.5.3")
//            classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
//            classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.2.0-rc04")
//        }
//    }

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
    buildFeatures{
        viewBinding = true
    }
//    viewBinding {
//        enabled = true
//    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("androidx.fragment:fragment-ktx:1.5.5")
//    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.facebook.android:facebook-android-sdk:17.0.2")
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("com.google.firebase:firebase-auth")
    implementation ("com.google.firebase:firebase-firestore")

//    implementation(fileTree(dir: 'libs', include: ['*.jar']))
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
//    implementation("androidx.appcompat:appcompat:1.1.0")
//    implementation("androidx.core:core-ktx:1.1.0")
//    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
//    implementation("androidx.legacy:legacy-support-v4:1.0.0")
//    implementation("com.google.firebase:firebase-database:19.2.1")
//    testImplementation("junit:junit:4.12")
//    androidTestImplementation("androidx.test.ext:junit:1.1.1")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //Android Navigation Architecture
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.4")


    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
//    implementation("com.google.firebase:firebase-database")
}