plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)                      // ← thêm
    `maven-publish`                              // ← thêm
}

android {
    namespace = "com.hnl.stringutils"
    compileSdk = 36                              // ← sửa cú pháp

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17   // ← nâng lên 17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"                               // ← nâng lên 17
    }

    publishing {
        singleVariant("release") {                     // ← thêm
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Thêm các lib dùng KSP ở đây, ví dụ Room:
    // implementation(libs.androidx.room.runtime)
    // ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.YOUR_GITHUB_USERNAME"
                artifactId = "StringUtilsSDK"
                version = "1.0.0"
            }
        }
    }
}