package dev.synapsetech.bots.moderation.data

import dev.synapsetech.bots.moderation.framework.RequiredConfig
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class ModerationBotConfig(
    override val token: String,
) : RequiredConfig {
    companion object {
        fun loadFile(file: File) = Json.decodeFromString<ModerationBotConfig>(file.readText())
    }
}