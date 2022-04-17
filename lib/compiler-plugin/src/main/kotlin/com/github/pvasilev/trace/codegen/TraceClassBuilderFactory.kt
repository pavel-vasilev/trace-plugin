package com.github.pvasilev.trace.codegen

import org.jetbrains.kotlin.codegen.ClassBuilder
import org.jetbrains.kotlin.codegen.ClassBuilderFactory
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin

class TraceClassBuilderFactory(private val delegate: ClassBuilderFactory) : ClassBuilderFactory by delegate {
    override fun newClassBuilder(origin: JvmDeclarationOrigin): ClassBuilder {
        val classBuilder = delegate.newClassBuilder(origin)
        return TraceClassBuilder(classBuilder)
    }
}