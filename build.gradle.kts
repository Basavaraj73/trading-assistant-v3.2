plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace="com.tradingassistant.v3"
    compileSdk=36
    defaultConfig {
        applicationId="com.tradingassistant.v3"
        minSdk=26
        targetSdk=36
        versionCode=3
        versionName="3.0"
    }
}
