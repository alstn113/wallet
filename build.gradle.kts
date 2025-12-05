import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring) apply false
	alias(libs.plugins.spring.boot) apply false
	alias(libs.plugins.spring.dependency.management)
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

kotlin {
	compilerOptions {
		jvmToolchain(21)
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allprojects {
	group = "io.github.alstn113"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	dependencyManagement {
		imports {
			mavenBom(rootProject.libs.spring.cloud.bom.get().toString())
		}
	}

	dependencies {
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		testImplementation(rootProject.libs.kotest.runner.junit5)
		testImplementation(rootProject.libs.kotest.assertions.core)
		testImplementation(rootProject.libs.mockk)
		testImplementation(rootProject.libs.springmockk)
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	}

	tasks.withType(Jar::class) {
		enabled = true
	}

	tasks.withType(BootJar::class) {
		enabled = false
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
