import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "org.qldmj"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven.aliyun.com/repository/gradle-plugin")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/central")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("com.squareup.retrofit2:retrofit:2.6.0")
                implementation("com.squareup.retrofit2:converter-gson:2.6.0")
            }
        }
        val jvmTest by getting
    }
}

task("copyWix311", Copy::class) {
    from("wix311.zip")
    into("build/wixToolset")
}

afterEvaluate {
    tasks.getByName("packageMsi").dependsOn("copyWix311")
    tasks.getByName("downloadWix").enabled = false

    if (file("build/wixToolset/unpacked").exists()) {
        tasks.getByName("unzipWix").enabled = false
    }
}

compose.desktop {
    application {
        javaHome = "C:/Program Files/Java/jdk-17"
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "compose-desktop"
            packageVersion = "1.0.0"

//            windows {
//                iconFile.set(project.file("icon.ico"))
//            }
            outputBaseDir.set(project.buildDir.resolve("customOutputDir"))
        }
//        打包为可执行的jar
//        disableDefaultConfiguration()
//        fromFiles(project.fileTree("libs/") { include("**/*.jar") })
//        mainJar.set(project.file("main.jar"))
//        dependsOn("mainJarTask")
    }
}
