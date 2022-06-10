package dev.synapsetech.bots.moderation.framework.command

import dev.synapsetech.bots.moderation.framework.Bot
import dev.synapsetech.bots.moderation.framework.Service
import net.dv8tion.jda.api.hooks.ListenerAdapter

class CommandService(
    private val bot: Bot<*>
) : ListenerAdapter(), Service {
    val commands: MutableMap<String, Command> = mutableMapOf()

    fun addCommand(command: Command) {
        commands[command.label] = command
    }

    override fun start() {
        commands.forEach { (_, cmd) ->
            bot.jda?.upsertCommand(cmd.label, cmd.description)?.queue()
        }
    }
    override fun shutdown() {}
}