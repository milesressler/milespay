import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.gradle.node.npm.task.NpmTask

plugins {
	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("com.github.node-gradle.node") version "3.3.0"
}

group = "us.milessmiles.extend"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

}

// Read more about how to configure the plugin from
// https://github.com/srs/gradle-node-plugin/blob/master/docs/node.md
node {
	nodeProjectDir.set(file("${project.projectDir}/src/main/webapp"))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<NpmTask>("appNpmBuild") {
	description = "Builds production version of the webapp"

	environment.set(mapOf("BUILD_PATH" to "${project.projectDir}/src/main/resources/static"))
	args.set(listOf("run", "build"))
}

tasks.register<NpmTask>("appNpmServe") {
	description = "Serves development version of the webapp"

	args.set(listOf("run", "start"))
}

//task copyWebApp(type: Copy) {
//	from 'src/main/webapp/build'
//	into 'build/resources/main/static/.'
//}
