package nl.janvanrosmalen.human

object XZero : Zero {
    override val xZero = XZero
    override val xBit = BitZero
    override val xDigit = DigitZero

    override val zero = XZero
    override val bit = BitZero
    override val digit = DigitZero

    override val long = 0L

    override fun hashCode() = long.hashCode()
    override fun equals(other: Any?) = long == other
    override fun toString() = long.toString()

    override fun compareTo(other: Zero) = 0
}