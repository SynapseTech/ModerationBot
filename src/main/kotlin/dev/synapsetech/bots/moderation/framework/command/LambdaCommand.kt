package dev.synapsetech.bots.moderation.framework.command

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class LambdaCommand(
    label: String,
    description: String,
    allowDm: Boolean = false,
    val block: LambdaCommandExecutionContext.() -> Unit
) : Command(label, description, allowDm) {
    override fun handle(event: SlashCommandInteractionEvent) {
        LambdaCommandExecutionContext(this, event).block()
    }

    data class LambdaCommandExecutionContext(
        val command: LambdaCommand,
        val event: SlashCommandInteractionEvent
    )
}