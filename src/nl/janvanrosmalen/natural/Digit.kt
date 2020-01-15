package nl.janvanrosmalen.natural

import java.math.BigInteger

sealed class Digit(override val int : Int) : Comparable<Digit>, Digitable {

    override fun compareTo(other: Digit): Int {
        return int.compareTo(other.int)
    }

    override fun toString() = int.toString()

    override val digit: Digit get() = this

    override val bit: Bit? get() = null

    override val zero :Zero? = null

}

object DigitZero : Digit(0){
    override val zero = Zero
    override val bit = BitZero
}

object DigitOne : Digit(1){
    override val bit = BitOne
}

object DigitTwo : Digit(2)

object DigitThree : Digit(3)

object DigitFour : Digit(4)

object DigitFive : Digit(5)

object DigitSix : Digit(6)

object DigitSeven : Digit(7)

object DigitEight : Digit(8)

object DigitNine : Digit(9)

val Int.digit : Digit? get() = when(this) {
    0 -> DigitZero
    1 -> DigitOne
    2 -> DigitTwo
    3 -> DigitThree
    4 -> DigitFour
    5 -> DigitFive
    6 -> DigitSix
    7 -> DigitSeven
    8 -> DigitEight
    9 -> DigitNine
    else -> null
}

val Long.digit : Digit? get() = when(this) {
    0L -> DigitZero
    1L -> DigitOne
    2L -> DigitTwo
    3L -> DigitThree
    4L -> DigitFour
    5L -> DigitFive
    6L -> DigitSix
    7L -> DigitSeven
    8L -> DigitEight
    9L -> DigitNine
    else -> null
}




