package com.github.pvasilev.trace

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.config.CompilerConfiguration

@AutoService(CommandLineProcessor::class)
class TraceCommandLineProcessor : CommandLineProcessor {
    override val pluginId: String = "trace"

    override val pluginOptions: Collection<AbstractCliOption> =
        listOf(
            CliOption("enabled", "<true|false", "Enable method tracing")
        )

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) {
        when(option.optionName) {
            "enabled" -> configuration.put(KEY_ENABLED, value.toBoolean())
        }
    }
}