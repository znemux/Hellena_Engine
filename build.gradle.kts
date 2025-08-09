group = "org.example"
version = "1.0-SNAPSHOT"

plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

//project.ext.lwjglVersion = "3.2.3"
//project.ext.jomlVersion = "1.10.8"
//project.ext.lwjglNatives = "natives-windows"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.lwjgl:lwjgl-bom:3.2.3"))

    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-assimp")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-nfd")
    implementation("org.lwjgl:lwjgl-openal")
    implementation("org.lwjgl:lwjgl-opengl")
    implementation("org.lwjgl:lwjgl-stb")
    implementation("org.lwjgl:lwjgl::natives-windows")
    implementation("org.lwjgl:lwjgl-assimp::natives-windows")
    implementation("org.lwjgl:lwjgl-glfw::natives-windows")
    implementation("org.lwjgl:lwjgl-nfd::natives-windows")
    implementation("org.lwjgl:lwjgl-openal::natives-windows")
    implementation("org.lwjgl:lwjgl-opengl::natives-windows")
    implementation("org.lwjgl:lwjgl-stb::natives-windows")
    implementation("org.joml:joml:1.10.8")

    // IMGUI-Java stuff
    implementation("io.github.spair:imgui-java-app:1.89.0")
}