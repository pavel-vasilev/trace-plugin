package com.github.pvasilev.trace

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class TraceGradlePlugin : KotlinCompilerPluginSupportPlugin {
    override fun apply(target: Project) {
        target.extensions.create("trace", TraceExtension::class.java)
    }

    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
        val project = kotlinCompilation.target.project
        val extension = project.extensions.findByName("trace") as? TraceExtension
        val enabled = extension?.enabled ?: true
        return project.provider {
            listOf(SubpluginOption("enabled", enabled.toString()))
        }
    }

    override fun getCompilerPluginId(): String = "trace"

    override fun getPluginArtifact(): SubpluginArtifact =
        SubpluginArtifact("com.github.pvasilev", "trace-gradle-plugin", "1.0.0")

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean = true
}

abstract class TraceExtension {
    abstract var enabled: Boolean
}