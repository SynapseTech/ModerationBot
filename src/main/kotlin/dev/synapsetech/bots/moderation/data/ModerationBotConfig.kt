package dev.synapsetech.bots.moderation.data

import dev.synapsetech.bots.moderation.framework.RequiredConfig
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class MongoConfiguration(
    val connectionString: String,
    val database: String,
)

@Serializable
data class ModerationBotConfig(
    override val token: String,
    override val devServerId: Long,
    override val dev: Boolean? = false,
    val mongo: MongoConfiguration,
) : RequiredConfig {
    companion object {
        lateinit var instance: ModerationBotConfig

        fun loadFile(file: File): ModerationBotConfig {
            instance = Json.decodeFromString(file.readText())
            return instance
        }
    }
}