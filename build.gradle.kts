// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.appdistribution") version "5.0.0" apply false
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }
}