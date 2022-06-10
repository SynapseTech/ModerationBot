package dev.synapsetech.bots.moderation.framework.command

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

abstract class Command(
    val label: String,
    val description: String,
) : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name == label) handle(event)
    }

    abstract fun handle(event: SlashCommandInteractionEvent)
}