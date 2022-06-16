package dev.synapsetech.bots.moderation.framework

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class Bot<T : RequiredConfig>(val config: T) {
    var jda: JDA? = null
    private val jdaBuilder: JDABuilder = JDABuilder.createDefault(config.token, GatewayIntent.GUILD_MEMBERS)
    val services: MutableList<Service> = mutableListOf()

    init {
        Runtime.getRuntime().addShutdownHook(Thread {
            shutdown()
        })
    }

    fun getLogger(): Logger = LoggerFactory.getLogger(Bot::class.java)

    fun addService(service: Service) {
        services += service
    }

    fun start() {
        services.forEach {
            it.start()
            if (it is ListenerAdapter) jdaBuilder.addEventListeners(it)
        }
        jda = jdaBuilder.build()
    }

    fun shutdown() {
        services.forEach { it.shutdown() }
        jda?.shutdown()
    }
}