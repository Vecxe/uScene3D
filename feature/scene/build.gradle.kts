import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("com.android.library")
  kotlin("android")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  compilerOptions {
    jvmTarget.set(JvmTarget.JVM_11)
  }
}

android {
  namespace = "org.robok.engine.feature.scene"
  compileSdk = 33

  defaultConfig {
    minSdk = 26
    vectorDrawables.useSupportLibrary = true
  }
    
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  

}

dependencies {
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("androidx.fragment:fragment-ktx:1.6.1")
  
  implementation("com.badlogicgames.gdx:gdx:1.9.14")
  implementation("com.badlogicgames.gdx:gdx-backend-android:1.9.14")
  implementation("com.badlogicgames.gdx:gdx-platform:1.9.14:natives-armeabi")
  implementation("com.badlogicgames.gdx:gdx-platform:1.9.14:natives-armeabi-v7a")
  implementation("com.badlogicgames.gdx:gdx-platform:1.9.14:natives-x86")
  implementation("com.badlogicgames.gdx:gdx-platform:1.9.14:natives-x86_64")
    
  implementation("com.github.mgsx-dev.gdx-gltf:gltf:2.2.1")
  
  implementation("org.lwjgl:lwjgl:3.3.1") // Núcleo do LWJGL
    implementation("org.lwjgl:lwjgl-opengl:3.3.1") // OpenGL para trabalhar com texturas
    implementation("org.lwjgl:lwjgl-stb:3.3.1") // STBImage para carregar imagens HDR
    implementation("org.lwjgl:lwjgl-glfw:3.3.1") // GLFW, caso precise para o contexto de janela, pode ser útil no futuro
}