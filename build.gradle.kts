plugins {
	id("org.jetbrains.kotlin.jvm") version "1.5.0"
	id("fabric-loom") version "0.8-SNAPSHOT"
	`maven-publish`
}

val modId: String by project
val minecraftVersion: String by project
val loaderVersion: String by project
val yarnMappings: String by project
val fabricVersion: String by project
val modVersion: String by project
val mavenGroup: String by project
val fabricKotlinVersion: String by project

base.archivesBaseName = modId
version = modVersion
group = mavenGroup

repositories {
	maven {
		name = "Curseforge Maven"
		url = uri("https://www.cursemaven.com")
	}

	maven {
		name = "Devan-Kerman/Devan-Repo"
		url = uri("https://raw.githubusercontent.com/Devan-Kerman/Devan-Repo/master/")
	}
	
	maven {
		name = "Bikeshedaniel Maven"
		url = uri("https://maven.shedaniel.me/")
	}
	
	maven {
		name = "TerraformersMC"
		url = uri("https://maven.terraformersmc.com/")
	}
	
	maven {
		name = "JitPack"
		url = uri("https://jitpack.io")
	}
	
	maven {
		url = uri("https://bai.jfrog.io/artifactory/maven")
	}
}

val modImplementationInclude by configurations.register("modImplementationInclude")

dependencies {
	minecraft("net.minecraft", "minecraft", minecraftVersion)
	mappings("net.fabricmc", "yarn", yarnMappings, classifier = "v2")

	modImplementation("net.fabricmc", "fabric-loader", loaderVersion)
	modImplementation("net.fabricmc.fabric-api", "fabric-api", fabricVersion)
	modImplementation("net.fabricmc", "fabric-language-kotlin", fabricKotlinVersion)
	
//	modImplementationInclude("curse.maven", "architectury-fabric-419697", "3366202")
//	modImplementationInclude("curse.maven", "cloth-config-319057", "3329246")
	modImplementationInclude("net.devtech", "grossfabrichacks", "6.1")
//
//	modRuntime("curse.maven", "roughly-enough-items-310111", "3366299")
	modRuntime("com.terraformersmc", "modmenu", "2.0.2")
	modRuntime("mcp.mobius.waila", "wthit", "fabric-3.6.1")
	
	add(sourceSets.main.get().getTaskName("mod", JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME), modImplementationInclude)
	add(net.fabricmc.loom.util.Constants.Configurations.INCLUDE, modImplementationInclude)
}

java {
	sourceCompatibility = JavaVersion.VERSION_16
	targetCompatibility = JavaVersion.VERSION_16
}

publishing {
	publications {
	
	}
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"

	options.release.set(16)
}

tasks.withType<AbstractArchiveTask> {
	from(file("LICENSE"))
}

tasks.processResources {
	filesMatching("fabric.mod.json") {
		expand("version" to project.version)
	}
}
