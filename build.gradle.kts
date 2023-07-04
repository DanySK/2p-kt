import io.github.gciatto.kt.mpp.Plugins
import io.github.gciatto.kt.mpp.ProjectType
import io.github.gciatto.kt.mpp.log
import io.github.gciatto.kt.mpp.nodeVersion
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.Detekt

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gitSemVer)
    alias(libs.plugins.ktMpp.multiProjectHelper)
}

group = "it.unibo.tuprolog"

gitSemVer {
    excludeLightweightTags()
    assignGitSemanticVersion()
}

log("version: $version", LogLevel.LIFECYCLE)

multiProjectHelper {
    defaultProjectType = ProjectType.KOTLIN // default project type for all projects which are not explicitly marked

    jvmProjects(":examples", ":ide", ":ide-plp", ":parser-jvm") // marks projects as JVM-only
    jsProjects(":parser-js") // marks projects as JS-only
    otherProjects(":documentation") // marks projects as non-Kotlin related

    val baseProjectTemplate = buildSet {
        add(Plugins.documentation)
        add(Plugins.linter)
        add(Plugins.bugFinder)
        add(Plugins.versions)
    }

    ktProjectTemplate = buildSet {
        addAll(baseProjectTemplate)
        add(Plugins.multiplatform)
    }

    jvmProjectTemplate = buildSet {
        addAll(baseProjectTemplate)
        add(Plugins.jvmOnly)
    }

    jsProjectTemplate = buildSet {
        addAll(baseProjectTemplate)
        add(Plugins.jsOnly)
    }

    otherProjectTemplate = buildSet {
        add(Plugins.versions)
    }

    applyProjectTemplates()
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
    plugins.withType<DetektPlugin> {
        val check by tasks.getting
        val detektAll by tasks.creating { group = "verification" }
        tasks.withType<Detekt>()
            .matching { task -> task.name.let { it.endsWith("Main") || it.endsWith("Test") } }
            .all {
                check.dependsOn(this)
                detektAll.dependsOn(this)
            }
    }
}

project.findProperty("nodeVersion")?.let { it.toString() }?.takeIf { it.isNotBlank() }?.let {
    nodeVersion(it)
    log("override NodeJS version: $it", LogLevel.LIFECYCLE)
}
