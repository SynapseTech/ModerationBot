package dev.synapsetech.bots.moderation.framework

interface RequiredConfig {
    val token: String
    val devServerId: Long
    val dev: Boolean?

    val isDev get() = dev == true
}