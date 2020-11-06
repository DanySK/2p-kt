import kotlin.String

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions`
 */
object Libs {
    /**
     * https://github.com/FasterXML/jackson-dataformat-xml
     */
    const val jackson_dataformat_xml: String =
            "com.fasterxml.jackson.dataformat:jackson-dataformat-xml:" +
            Versions.com_fasterxml_jackson_dataformat

    /**
     * https://github.com/FasterXML/jackson-dataformats-text
     */
    const val jackson_dataformat_yaml: String =
            "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:" +
            Versions.com_fasterxml_jackson_dataformat

    /**
     * https://javaeden.github.io/Orchid/latest/core/
     */
    const val orchiddocs: String = "io.github.javaeden.orchid:OrchidDocs:" +
            Versions.io_github_javaeden_orchid

    /**
     * https://javaeden.github.io/Orchid/latest/core/
     */
    const val orchidkotlindoc: String = "io.github.javaeden.orchid:OrchidKotlindoc:" +
            Versions.io_github_javaeden_orchid

    /**
     * https://javaeden.github.io/Orchid/latest/core/
     */
    const val orchidplugindocs: String = "io.github.javaeden.orchid:OrchidPluginDocs:" +
            Versions.io_github_javaeden_orchid

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_reflect: String = "org.jetbrains.kotlin:kotlin-reflect:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_scripting_compiler_embeddable: String =
            "org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_stdlib_common: String = "org.jetbrains.kotlin:kotlin-stdlib-common:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_stdlib_jdk8: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_stdlib_js: String = "org.jetbrains.kotlin:kotlin-stdlib-js:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_test_annotations_common: String =
            "org.jetbrains.kotlin:kotlin-test-annotations-common:" + Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_test_common: String = "org.jetbrains.kotlin:kotlin-test-common:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_test_js: String = "org.jetbrains.kotlin:kotlin-test-js:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_test_junit: String = "org.jetbrains.kotlin:kotlin-test-junit:" +
            Versions.org_jetbrains_kotlin

    /**
     * https://openjdk.java.net/projects/openjfx/
     */
    const val javafx_base: String = "org.openjfx:javafx-base:" + Versions.org_openjfx

    /**
     * https://openjdk.java.net/projects/openjfx/
     */
    const val javafx_controls: String = "org.openjfx:javafx-controls:" + Versions.org_openjfx

    /**
     * https://openjdk.java.net/projects/openjfx/
     */
    const val javafx_fxml: String = "org.openjfx:javafx-fxml:" + Versions.org_openjfx

    /**
     * https://openjdk.java.net/projects/openjfx/
     */
    const val javafx_graphics: String = "org.openjfx:javafx-graphics:" + Versions.org_openjfx

    /**
     * http://www.antlr.org
     */
    const val antlr4: String = "org.antlr:antlr4:" + Versions.org_antlr

    /**
     * http://www.antlr.org
     */
    const val antlr4_runtime: String = "org.antlr:antlr4-runtime:" + Versions.org_antlr

    const val org_danilopianini_git_sensitive_semantic_versioning_gradle_plugin: String =
            "org.danilopianini.git-sensitive-semantic-versioning:org.danilopianini.git-sensitive-semantic-versioning.gradle.plugin:" +
            Versions.org_danilopianini_git_sensitive_semantic_versioning_gradle_plugin

    const val com_github_breadmoirai_github_release_gradle_plugin: String =
            "com.github.breadmoirai.github-release:com.github.breadmoirai.github-release.gradle.plugin:" +
            Versions.com_github_breadmoirai_github_release_gradle_plugin

    const val org_jetbrains_kotlin_multiplatform_gradle_plugin: String =
            "org.jetbrains.kotlin.multiplatform:org.jetbrains.kotlin.multiplatform.gradle.plugin:" +
            Versions.org_jetbrains_kotlin_multiplatform_gradle_plugin

    const val io_github_gciatto_kt_npm_publish_gradle_plugin: String =
            "io.github.gciatto.kt-npm-publish:io.github.gciatto.kt-npm-publish.gradle.plugin:" +
            Versions.io_github_gciatto_kt_npm_publish_gradle_plugin

    const val com_github_johnrengelman_shadow_gradle_plugin: String =
            "com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin:" +
            Versions.com_github_johnrengelman_shadow_gradle_plugin

    const val org_jlleitschuh_gradle_ktlint_gradle_plugin: String =
            "org.jlleitschuh.gradle.ktlint:org.jlleitschuh.gradle.ktlint.gradle.plugin:" +
            Versions.org_jlleitschuh_gradle_ktlint_gradle_plugin

    const val de_fayard_buildsrcversions_gradle_plugin: String =
            "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
            Versions.de_fayard_buildsrcversions_gradle_plugin

    const val org_openjfx_javafxplugin_gradle_plugin: String =
            "org.openjfx.javafxplugin:org.openjfx.javafxplugin.gradle.plugin:" +
            Versions.org_openjfx_javafxplugin_gradle_plugin

    const val com_eden_orchidplugin_gradle_plugin: String =
            "com.eden.orchidPlugin:com.eden.orchidPlugin.gradle.plugin:" +
            Versions.com_eden_orchidplugin_gradle_plugin

    const val org_jetbrains_dokka_gradle_plugin: String =
            "org.jetbrains.dokka:org.jetbrains.dokka.gradle.plugin:" +
            Versions.org_jetbrains_dokka_gradle_plugin

    const val com_jfrog_bintray_gradle_plugin: String =
            "com.jfrog.bintray:com.jfrog.bintray.gradle.plugin:" +
            Versions.com_jfrog_bintray_gradle_plugin

    /**
     * https://github.com/FasterXML/jackson-modules-java8
     */
    const val jackson_datatype_jsr310: String =
            "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:" +
            Versions.jackson_datatype_jsr310

    /**
     * https://github.com/ajalt/clikt
     */
    const val clikt_multiplatform: String = "com.github.ajalt:clikt-multiplatform:" +
            Versions.clikt_multiplatform

    /**
     * https://github.com/FasterXML/jackson-core
     */
    const val jackson_core: String = "com.fasterxml.jackson.core:jackson-core:" +
            Versions.jackson_core

    /**
     * https://github.com/FXMisc/RichTextFX/#richtextfx
     */
    const val richtextfx: String = "org.fxmisc.richtext:richtextfx:" + Versions.richtextfx

    /**
     * http://plantuml.sourceforge.net
     */
    const val plantuml: String = "net.sourceforge.plantuml:plantuml:" + Versions.plantuml

    /**
     * https://github.com/gciatto/kt-math
     */
    const val kt_math: String = "io.github.gciatto:kt-math:" + Versions.kt_math

    /**
     * https://github.com/pinterest/ktlint
     */
    const val ktlint: String = "com.pinterest:ktlint:" + Versions.ktlint
}
