package dev.synapsetech.util

sealed class Either<out L, out R>(open val value: Any) {
    data class Left<out L : Any>(override val value: L) : Either<L, Nothing>(value)
    data class Right<out R : Any>(override val value: R) : Either<Nothing, R>(value)
}