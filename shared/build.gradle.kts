plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serializationPlugin)

}


@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../moviesMp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        //val ktorVersion = "2.3.5"
        val coroutinesVersion = "1.7.1"
        val ktorVersion = "2.3.5"
        val sqlDelightVersion = "1.5.5"
        val dateTimeVersion = "0.4.0"
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-json:$ktorVersion")
                //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation ("io.ktor:ktor-http-cio:$ktorVersion")
                implementation ("io.ktor:ktor-client-cio:$ktorVersion")
                implementation ("androidx.annotation:annotation:1.3.0")
                //implementation("org.jetbrains.kotlin.plugin.serialization:1.6.0-RC")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")

                //classpath("org.jetbrains.kotlin:kotlin-serialization:1.5.21")


                //implementation("org.jetbrains.kotlin.plugin.serialization' version '1.6.21")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4") }
        }

        val iosMain by getting {
            // ...
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }

        /*val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }*/

    }
}

android {
    namespace = "co.softwarejourneys.moviesmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}
dependencies {
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
}
