package dev.synapsetech.bots.moderation

import dev.synapsetech.bots.moderation.data.ModerationBotConfig
import dev.synapsetech.bots.moderation.framework.Bot

class BotImpl(config: ModerationBotConfig) : Bot<ModerationBotConfig>(config) {
    init {
        instance = this
    }

    fun genInvite() =
        "https://discord.com/api/oauth2/authorize?client_id=${jda?.selfUser?.id}&permissions=1505319316727&scope=bot%20applications.commands"

    companion object {
        lateinit var instance: BotImpl
    }
}