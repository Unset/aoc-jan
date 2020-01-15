package nl.janvanrosmalen.natural

import xpair.Duo
import java.math.BigInteger

sealed class Bit(override val int : Int) : Comparable<Bit>, Bitable {
    abstract val other : Bit

    override val bit: Bit get() = this

    abstract override val digit : Digit

    override fun compareTo(other: Bit): Int {
        return int.compareTo(other.int)
    }

    //abstract override fun toString() : String
}

val selfPair = Duo<Bit>(
    BitZero,
    BitOne
)

object BitZero : Bit(0){
    override val digit = DigitZero
    override fun toString() = "0"
    override val zero: Zero = Zero
    override val other: Bit
        get() = BitOne
}

object BitOne : Bit(1){
    override val digit = DigitOne
    override val zero = null
    override fun toString() = "1"
    override val other: Bit
        get() = BitZero

}