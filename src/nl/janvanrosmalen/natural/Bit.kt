package nl.janvanrosmalen.natural

import xpair.Duo
import java.math.BigInteger

sealed class Bit : Comparable<Bit>, Digitable {
    abstract val other : Bit

    override fun compareTo(other: Bit): Int {
        return int.compareTo(other.int)
    }
}

val selfPair = Duo<Bit>(
    BitZero,
    BitOne
)

object BitZero : Bit(){
    override val int = 0
    override val long = 0L
    override val bigInteger = BigInteger.ZERO!!
    override val char = '0'
    override val string = "0"
    override val digit = Digit.ZERO

    override val other: Bit
        get() = BitOne
}

object BitOne : Bit(){
    override val int = 1
    override val long = 1L
    override val bigInteger = BigInteger.ONE!!
    override val char = '1'
    override val string = "1"
    override val digit = Digit.ONE

    override val other: Bit
        get() = BitZero

}