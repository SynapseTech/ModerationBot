package dev.synapsetech.util.ext

import dev.synapsetech.util.Either
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed

fun EmbedBuilder.build(): Either.Left<MessageEmbed> = Either.Left(build())