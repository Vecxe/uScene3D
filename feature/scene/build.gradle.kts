import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("com.android.library")
  kotlin("android")
}

android {
  namespace = "org.robok.engine.feature.scene"
  compileSdk = 34
  
  defaultConfig {
    minSdk = 26
    vectorDrawables.useSupportLibrary = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  compilerOptions {
    jvmTarget.set(JvmTarget.JVM_21)
  }
}

dependencies {
  implementation("androidx.appcompat:appcompat:1.7.0")
  implementation("androidx.fragment:fragment-ktx:1.8.5")

  implementation("com.badlogicgames.gdx:gdx:1.9.14")
  implementation("com.badlogicgames.gdx:gdx-backend-android:1.9.14")
  implementation("com.badlogicgames.gdx:gdx-platform:1.9.14:natives-armeabi")
  implementation("com.badlogicgames.gdx:gdx-platform:1.9.14:natives-armeabi-v7a")
  implementation("com.badlogicgames.gdx:gdx-platform:1.9.14:natives-x86")
  implementation("com.badlogicgames.gdx:gdx-platform:1.9.14:natives-x86_64")

  implementation("com.github.mgsx-dev.gdx-gltf:gltf:2.2.1")

  implementation("com.github.Robok-Engine:HdriToCubemap:2.0.0")

  implementation("org.lwjgl:lwjgl:3.3.1") // LWJGL Core
  implementation("org.lwjgl:lwjgl-opengl:3.3.1") // OpenGL for working with textures
  implementation("org.lwjgl:lwjgl-stb:3.3.1") // STBImage for loading HDR images
  implementation("org.lwjgl:lwjgl-glfw:3.3.1") // GLFW, if you need it for window context, it may be useful in the future
}
