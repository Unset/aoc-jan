package nl.janvanrosmalen.batshit

import java.util.*

sealed class ResourceIdentifier

object CustomIdentifier : ResourceIdentifier()
data class UniversalIdentifier(val uuid: UUID) : ResourceIdentifier()
data class NaturalIdentifier(val long : Long) : ResourceIdentifier() {
    companion object {
        val ZERO = NaturalIdentifier(0)
    }
}