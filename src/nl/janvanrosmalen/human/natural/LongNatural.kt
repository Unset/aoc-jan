package nl.janvanrosmalen.human.natural

internal class LongNatural(override val long : Long) : Natural {

    override val natural: Natural = this
    override val xZero: XZero? get() = if (long==0L) XZero else null
    override val xBit: XBit? get() = when (long){
        0L -> BitZero
        1L -> BitOne
        else -> null
    }

    override val xDigit: XDigit?
        get() = when (long){
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


    override fun compareTo(other: Natural) = other.long?.let {long.compareTo(it) } ?: -1

    override fun hashCode() = long.hashCode()
    override fun equals(other: Any?) = long == other
    override fun toString() = long.toString()
}