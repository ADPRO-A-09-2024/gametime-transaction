plugins {
	java
    jacoco
	id("org.sonarqube") version "4.4.1.3373"
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

sonar {
    properties {
        property("sonar.projectKey", "ADPRO-A-09-2024_gametime-transaction")
        property("sonar.organization", "adpro-a-09-2024")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.exclusions", "**/configs")
    }
}

group = "id.ac.ui.cs.advprog.gametime"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val junitJupiterVersion = "5.9.1"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.google.code.gson:gson")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("com.h2database:h2")
	implementation("io.micrometer:micrometer-registry-prometheus")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks.register<Test>("unitTest") {
	description = "Run unit tests."
	group = "verification"
	filter {
		excludeTestsMatching("*FunctionalTest")
	}
}

tasks.test {
    filter {
        excludeTestsMatching("*FunctionalTest")
    }

    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

	reports {
        html.required = true
        xml.required = true
    }
}

tasks.withType<Test>().configureEach {
	useJUnitPlatform()
}