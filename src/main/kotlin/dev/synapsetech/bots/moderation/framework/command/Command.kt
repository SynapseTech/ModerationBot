package dev.synapsetech.bots.moderation.framework.command

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
abstract class Command(
    val label: String,
    val description: String,
    val allowDm: Boolean = false,
    val requiredPermissions: List<Permission> = listOf()
) {
    abstract fun handle(event: SlashCommandInteractionEvent)
}