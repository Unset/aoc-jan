package nl.janvanrosmalen.human.natural

object Zero : ZeroComparable {

    override val zero = Zero
    override val bit = BitZero
    override val digit = DigitZero

    override val long = 0L
    override val natural : NaturalComparable = LongNaturalComparable(0L)

    override fun hashCode() = long.hashCode()
    override fun equals(other: Any?) = long == other
    override fun toString() = long.toString()

    override fun compareTo(other: ZeroComparable) = 0
}