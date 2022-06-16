package dev.synapsetech.bots.moderation.framework.command.hooks

import dev.synapsetech.bots.moderation.framework.command.Command
import dev.synapsetech.bots.moderation.framework.command.HookResult
import dev.synapsetech.bots.moderation.framework.command.PreCommandHook
import dev.synapsetech.util.Either
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.MessageBuilder
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class EnforceAllowDm(private val errorMessage: Either<MessageEmbed, Message>) : PreCommandHook {
    constructor(message: String) : this(Either.Right(MessageBuilder().setContent(message).build()))
    constructor(embedBuilder: EmbedBuilder): this(Either.Left(embedBuilder.build()))
    constructor(block: EmbedBuilder.() -> Unit) : this(Either.Left(EmbedBuilder().apply(block).build()))

    override fun run(event: SlashCommandInteractionEvent, command: Command): HookResult {
        if (event.channelType == ChannelType.PRIVATE && !command.allowDm) {
            if (errorMessage is Either.Right) event.reply(errorMessage.value).queue()
            else if (errorMessage is Either.Left) event.replyEmbeds(errorMessage.value).queue()

            return HookResult(true)
        }

        return HookResult()
    }
}