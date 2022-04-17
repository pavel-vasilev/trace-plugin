package com.github.pvasilev.trace

import com.github.pvasilev.trace.codegen.TraceClassBuilderInterceptorExtension
import com.google.auto.service.AutoService
import org.jetbrains.kotlin.codegen.extensions.ClassBuilderInterceptorExtension
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration

@AutoService(ComponentRegistrar::class)
class TraceComponentRegistrar : ComponentRegistrar {
    override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
        val enabled = configuration.get(KEY_ENABLED, true)
        if (enabled) {
            ClassBuilderInterceptorExtension.registerExtension(project, TraceClassBuilderInterceptorExtension())
        }
    }
}