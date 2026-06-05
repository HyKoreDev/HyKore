plugins {
    id("buildsrc.convention.kotlin-jvm")
    alias(libs.plugins.shadow)
    alias(libs.plugins.kotlinPluginSerialization)
}

dependencies {
    implementation(libs.kotlinxSerialization)
    implementation(libs.kotlinxCoroutines)
    implementation(project(":utils"))
    implementation(project(":hykore-api"))
}

dependencies {
    val hytaleServerJar: String by project

    implementation(kotlin("stdlib"))
    compileOnly(files(hytaleServerJar))
}
