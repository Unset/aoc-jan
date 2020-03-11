package nl.janvanrosmalen.human.natural

public class LongNaturalComparable(override val long : Long) : NaturalComparable, Longable {
    override val predecessor: Naturable?
        get() = if (long == 0L) null else LongNaturalComparable(long - 1)

    override val natural: NaturalComparable = this
    override val zero: Zero? get() = if (long==0L) Zero else null
    override val bit: Bit? get() = when (long){
        0L -> BitZero
        1L -> BitOne
        else -> null
    }

    override val digit: Digit?
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


    override fun compareTo(other: NaturalComparable) = other.long?.let {long.compareTo(it) } ?: -1

    override fun hashCode() = long.hashCode()
    override fun equals(other: Any?) = if (other is Longable) other.long == long else false
    override fun toString() = long.toString()
}