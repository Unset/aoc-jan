package nl.janvanrosmalen.human

sealed class XDigit(
    final override val predecessor: XDigit?,
    final override val xDigit: XDigit,
    final override val successor: XDigit?,
    final override val long : Long
) : Digit{
    override fun compareTo(other: Digit) = long.compareTo(other.long)

    override val digit: Digit get() = this

    override fun hashCode() = long.hashCode()
    override fun equals(other: Any?) = long == other
    override fun toString() = long.toString()
}


fun <T, R> T.dig(deeper : (T) -> T?, mine : (T) -> R?) : R? = mine(this) ?: deeper(this)?.dig(deeper, mine)

val Boolean.unit : Unit? get() = if (this) Unit else null

val Unit?.boolean : Boolean get() = this==Unit

object DigitZero : XDigit(null, DigitZero, DigitOne, 0)
object DigitOne : XDigit(DigitZero, DigitOne, DigitTwo, 1)
object DigitTwo : XDigit(DigitOne, DigitTwo, DigitThree, 2)
object DigitThree : XDigit(DigitTwo, DigitThree, DigitFour, 3)
object DigitFour : XDigit(DigitThree, DigitFour, DigitFive, 4)
object DigitFive : XDigit(DigitFour, DigitFive, DigitSix, 5)
object DigitSix : XDigit(DigitFive, DigitSix, DigitSeven , 6)
object DigitSeven : XDigit(DigitSix, DigitSeven, DigitEight, 7)
object DigitEight : XDigit(DigitSeven, DigitEight, DigitNine ,8)
object DigitNine : XDigit(DigitEight, DigitNine, null, 9)