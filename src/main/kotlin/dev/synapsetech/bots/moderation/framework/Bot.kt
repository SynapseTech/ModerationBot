package dev.synapsetech.bots.moderation.framework

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

class Bot<T : RequiredConfig>(val config: T) {
    var jda: JDA? = null
    private val jdaBuilder: JDABuilder = JDABuilder.createDefault(config.token, GatewayIntent.GUILD_MEMBERS)
    val services: MutableList<Service> = mutableListOf()

    init {
        Runtime.getRuntime().addShutdownHook(Thread {
            services.forEach { it.shutdown() }
            jda?.shutdown()
        })
    }

    fun addService(service: Service) {
        services += service
    }

    fun start() {
        jda = jdaBuilder.build()
        services.forEach { it.start() }
    }
}