package dev.synapsetech.bots.moderation.data.models

import dev.synapsetech.bots.moderation.data.Mongo
import kotlinx.serialization.Serializable

@Serializable
data class ServerSettings(
    val serverId: Long,
) {
    companion object {
        private const val COLLECTION_NAME = "servers"

        fun getCollection() = Mongo.collection<ServerSettings>(COLLECTION_NAME)
    }
}