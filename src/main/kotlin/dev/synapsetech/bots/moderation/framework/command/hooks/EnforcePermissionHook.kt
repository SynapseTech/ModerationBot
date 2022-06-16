package dev.synapsetech.bots.moderation.framework.command.hooks

import dev.synapsetech.bots.moderation.framework.command.Command
import dev.synapsetech.bots.moderation.framework.command.HookResult
import dev.synapsetech.bots.moderation.framework.command.PreCommandHook
import dev.synapsetech.util.Either
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class EnforcePermissionHook(
    val buildErrorMessage: (permissions: List<Permission>) -> Either<MessageEmbed, Message>
) : PreCommandHook {
    override fun run(event: SlashCommandInteractionEvent, command: Command): HookResult {
        var allow = true
        command.requiredPermissions.forEach {
            if (event.member?.hasPermission(it) != true) allow = false
        }

        return if (!allow) {
            val errorMessage = buildErrorMessage(command.requiredPermissions)

            if (errorMessage is Either.Right) event.reply(errorMessage.value).queue()
            else if (errorMessage is Either.Left) event.replyEmbeds(errorMessage.value).queue()

            HookResult(true)
        } else HookResult()
    }
}