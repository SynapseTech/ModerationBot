package dev.synapsetech.bots.moderation

import dev.synapsetech.bots.moderation.data.ModerationBotConfig
import java.io.File

fun main(args: Array<String>) {
    val configFile = if (args.isEmpty()) {
        println("No config specified, defaulting to ./config.json")
        "./config.json"
    } else args[0]
    val cfg = ModerationBotConfig.loadFile(File(configFile))
    val bot = BotImpl(cfg)
    bot.start()
}