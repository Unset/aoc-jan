package nl.janvanrosmalen.natural

import java.math.BigInteger

interface Natural {
    val bigInteger : BigInteger
    val naturalLong : NaturalLong?
    val naturalInt : NaturalInt?
    val digit : Digit?
    val bit : Bit?
    val zero : Zero?
    override fun toString() : String
}