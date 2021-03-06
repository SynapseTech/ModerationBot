package dev.synapsetech.bots.moderation

import dev.synapsetech.bots.moderation.data.ModerationBotConfig
import dev.synapsetech.bots.moderation.data.Mongo
import dev.synapsetech.bots.moderation.framework.command.CommandService
import dev.synapsetech.bots.moderation.framework.command.LambdaCommand
import dev.synapsetech.bots.moderation.framework.command.hooks.EnforceAllowDm
import dev.synapsetech.bots.moderation.framework.command.hooks.EnforcePermissionHook
import dev.synapsetech.util.Either
import net.dv8tion.jda.api.EmbedBuilder
import java.io.File

fun main(args: Array<String>) {
    val configFile = if (args.isEmpty()) {
        println("No config specified, defaulting to ./config.json")
        "./config.json"
    } else args[0]
    val cfg = ModerationBotConfig.loadFile(File(configFile))
    Mongo.connect(cfg.mongo)

    val bot = BotImpl(cfg)

    val commandService = CommandService(bot)

    commandService.addHook(EnforceAllowDm {
        setDescription("This command must be used in an authorized server.")
    })

    commandService.addHook(EnforcePermissionHook { perms ->
        val eb = EmbedBuilder()

        val permsJoined = perms.joinToString(",") { "`${it.getName()}`" }
        eb.setDescription("You are missing the following permissions to complete this action: $permsJoined.")

        Either.Left(eb.build())
    })

    commandService.addCommand(LambdaCommand("ping", "Check the bot's gateway ping", true) {
        val time = System.currentTimeMillis()
        event.reply("Ping!").queue {
            it.editOriginalFormat("Pong: `%d ms`", System.currentTimeMillis() - time).queue()
        }
    })

    bot.addService(commandService)
    bot.start()
}