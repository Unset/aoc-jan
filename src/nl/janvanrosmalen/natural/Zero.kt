package nl.janvanrosmalen.natural

import java.math.BigInteger

object Zero : Comparable<Zero>, Zeroable {
    override fun compareTo(other: Zero) = 0
    override fun toString() = "0"
    override val bigInteger = BigInteger.ZERO
}