package dev.synapsetech.bots.moderation

import dev.synapsetech.bots.moderation.data.ModerationBotConfig
import dev.synapsetech.bots.moderation.framework.Bot

class BotImpl(config: ModerationBotConfig) : Bot<ModerationBotConfig>(config) {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: BotImpl
    }
}