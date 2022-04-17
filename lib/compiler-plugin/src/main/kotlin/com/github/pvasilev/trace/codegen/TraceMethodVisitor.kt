package com.github.pvasilev.trace.codegen

import org.jetbrains.org.objectweb.asm.MethodVisitor
import org.jetbrains.org.objectweb.asm.Opcodes
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter

class TraceMethodVisitor(
    private val sectionName: String,
    original: MethodVisitor
) : MethodVisitor(Opcodes.ASM8, original) {
    override fun visitCode() {
        super.visitCode()
        InstructionAdapter(this).apply {
            visitLdcInsn(sectionName)
            invokestatic("android/os/Trace", "beginSection", "(Ljava/lang/String;)V", false)
        }
    }

    override fun visitInsn(opcode: Int) {
        val returnOpcodes = setOf(
            Opcodes.RETURN,
            Opcodes.IRETURN,
            Opcodes.LRETURN,
            Opcodes.FRETURN,
            Opcodes.DRETURN,
            Opcodes.ARETURN,
        )
        if (opcode in returnOpcodes) {
            InstructionAdapter(this).apply {
                invokestatic("android/os/Trace", "endSection", "()V", false)
            }
        }
        super.visitInsn(opcode)
    }
}