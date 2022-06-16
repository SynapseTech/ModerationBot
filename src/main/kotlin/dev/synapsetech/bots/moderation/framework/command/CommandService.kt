package dev.synapsetech.bots.moderation.framework.command

import dev.synapsetech.bots.moderation.framework.Bot
import dev.synapsetech.bots.moderation.framework.Service
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CommandService(
    private val bot: Bot<*>
) : ListenerAdapter(), Service {
    val commands: MutableMap<String, Command> = mutableMapOf()
    val hooks = mutableListOf<PreCommandHook>()

    init {
        instance = this
    }

    fun getLogger(): Logger = LoggerFactory.getLogger(CommandService::class.java)

    fun addHook(hook: PreCommandHook) {
        hooks += hook
    }

    fun addCommand(command: Command) {
        commands[command.label] = command
    }

    override fun start() {}
    override fun shutdown() {}

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val command = commands[event.name] ?: run { return@onSlashCommandInteraction }

        var cancel = false
        hooks.forEach {
            val res = it.run(event, command)
            if (res.cancel) cancel = true
        }
        if (cancel) return

        command.handle(event)
    }

    override fun onReady(event: ReadyEvent) {
        getLogger().info("Starting CommandService.")

        if (bot.config.isDev)
            getLogger().debug("Development mode ON, upserting commands to server with id ${bot.config.devServerId})")

        commands.forEach { (_, cmd) ->
            if (bot.config.isDev) {
                val devGuild = bot.jda?.getGuildById(bot.config.devServerId)
                getLogger().debug("Upserting command ${cmd.label} to dev guild ${devGuild?.name}")
                devGuild?.upsertCommand(cmd.label, cmd.description)?.queue()
            }

            bot.jda?.upsertCommand(cmd.label, cmd.description)?.queue()
        }
    }

    companion object {
        lateinit var instance: CommandService
    }
}