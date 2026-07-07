plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    // Add this line:
    alias(libs.plugins.androidx.navigation.safeargs) apply false
}