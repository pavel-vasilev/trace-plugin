package com.github.pvasilev.trace.codegen

import com.github.pvasilev.trace.Trace
import org.jetbrains.kotlin.codegen.ClassBuilder
import org.jetbrains.kotlin.codegen.DelegatingClassBuilder
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin
import org.jetbrains.org.objectweb.asm.MethodVisitor

class TraceClassBuilder(private val delegate: ClassBuilder) : DelegatingClassBuilder() {
    override fun getDelegate(): ClassBuilder = delegate

    override fun newMethod(
        origin: JvmDeclarationOrigin,
        access: Int,
        name: String,
        desc: String,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        val original = delegate.newMethod(origin, access, name, desc, signature, exceptions)
        val descriptor = origin.descriptor as? FunctionDescriptor ?: return original
        val fqName = FqName(requireNotNull(Trace::class.qualifiedName))
        val annotation = descriptor.annotations.findAnnotation(fqName) ?: return original
        val sectionName = annotation.allValueArguments.values.first().value.toString()
        return TraceMethodVisitor(sectionName, original)
    }
}