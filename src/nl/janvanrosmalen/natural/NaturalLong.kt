package nl.janvanrosmalen.natural

import java.math.BigInteger

interface NaturalLong : Natural {
    val long : Long
    override val bigInteger: BigInteger get() = BigInteger.valueOf(long)
    override val naturalLong : NaturalLong get() = this
    override fun toString() : String
}