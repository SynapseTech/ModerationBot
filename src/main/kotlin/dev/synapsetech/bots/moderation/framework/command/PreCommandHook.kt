package dev.synapsetech.bots.moderation.framework.command

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

interface PreCommandHook {
    fun run(event: SlashCommandInteractionEvent, command: Command): HookResult
}