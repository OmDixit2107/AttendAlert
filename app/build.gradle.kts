plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    kotlin("plugin.serialization") version "1.7.20"
}

android {
    namespace = "com.example.attendalert"
    compileSdk = 34
//    compileSdkPreview "VanillaIceCream"

    defaultConfig {
        applicationId = "com.example.attendalert"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.navigation:navigation-compose:2.7.0")
    implementation("androidx.compose.material:material-icons-extended-android:1.6.8")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    implementation(libs.play.services.auth)
    implementation(libs.firebase.firestore.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
//

    // optional - needed for credentials support from play services, for devices running
    // Android 13 and below.
    implementation ("androidx.credentials:credentials:1.3.0-alpha01")
    // optional - needed for credentials support from play services, for devices running
    // Android 13 and below.
    implementation ("androidx.credentials:credentials-play-services-auth:1.3.0-alpha01")
    implementation ("com.google.android.libraries.identity.googleid:googleid:1.1.0")

    implementation(platform("io.github.jan-tennert.supabase:bom:2.5.0"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt:1.3.2")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:1.3.2")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:1.3.2")
    implementation("io.ktor:ktor-client-android:2.3.4")
//    implementation ("com.google.android.libraries.identity.googleid:googleid:<latest version>)"

}