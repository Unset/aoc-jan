package nl.janvanrosmalen.human.natural

sealed class Digit(
    final override val predecessor: Digit?,
    final override val digit: Digit,
    final override val successor10: Digitable?,
    final override val long : Long,
    final override val bit: Bit? = null,
    final override val zero : Zero? = null
) : DigitComparable {
    override fun compareTo(other: DigitComparable) = long.compareTo(other.long)

    final override val natural : NaturalComparable =
        LongNaturalComparable(long)

    override fun hashCode() = long.hashCode()
    override fun equals(other: Any?) = long == other
    override fun toString() = long.toString()
}


fun <T, R> T.dig(deeper : (T) -> T?, mine : (T) -> R?) : R? = mine(this) ?: deeper(this)?.dig(deeper, mine)

val Boolean.unit : Unit? get() = if (this) Unit else null

val Unit?.boolean : Boolean get() = this==Unit

object DigitZero : Digit(null,
    DigitZero,
    DigitOne, 0,
    BitZero,
    Zero
)
object DigitOne : Digit(
    DigitZero,
    DigitOne,
    DigitTwo, 1,
    BitOne
)
object DigitTwo : Digit(
    DigitOne,
    DigitTwo,
    DigitThree, 2)
object DigitThree : Digit(
    DigitTwo,
    DigitThree,
    DigitFour, 3)
object DigitFour : Digit(
    DigitThree,
    DigitFour,
    DigitFive, 4)
object DigitFive : Digit(
    DigitFour,
    DigitFive,
    DigitSix, 5)
object DigitSix : Digit(
    DigitFive,
    DigitSix,
    DigitSeven, 6)
object DigitSeven : Digit(
    DigitSix,
    DigitSeven,
    DigitEight, 7)
object DigitEight : Digit(
    DigitSeven,
    DigitEight,
    DigitNine,8)
object DigitNine : Digit(
    DigitEight,
    DigitNine, null, 9)