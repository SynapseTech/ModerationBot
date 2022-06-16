package dev.synapsetech.bots.moderation.framework.command.hooks

import dev.synapsetech.bots.moderation.framework.command.Command
import dev.synapsetech.bots.moderation.framework.command.HookResult
import dev.synapsetech.bots.moderation.framework.command.PreCommandHook
import dev.synapsetech.util.Either
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.MessageBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class EnforceBlacklist(
    private val errorMessage: Either<MessageEmbed, Message>,
    private val checkUser: (user: User) -> Boolean
) : PreCommandHook {
    constructor(message: String, checkUser: (user: User) -> Boolean)
            : this(Either.Right(MessageBuilder().setContent(message).build()), checkUser)
    constructor(embedBuilder: EmbedBuilder, checkUser: (user: User) -> Boolean)
            : this(Either.Left(embedBuilder.build()), checkUser)
    constructor(block: EmbedBuilder.() -> Unit, checkUser: (user: User) -> Boolean)
            : this(Either.Left(EmbedBuilder().apply(block).build()), checkUser)

    override fun run(event: SlashCommandInteractionEvent, command: Command): HookResult {
        val allow = checkUser(event.user)
        
        if (!allow) {
            if (errorMessage is Either.Right) event.reply(errorMessage.value).queue()
            else if (errorMessage is Either.Left) event.replyEmbeds(errorMessage.value).queue()

            return HookResult(true)
        }
        
        return HookResult()
    }
}