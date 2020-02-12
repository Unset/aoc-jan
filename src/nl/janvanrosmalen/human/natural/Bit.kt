package nl.janvanrosmalen.human.natural

sealed class Bit(
    final override val bit : Bit,
    final override val long : Long,
    final override val digit : Digit,
    final override val zero : Zero?,
    final override val successor10: Digitable,
    final override val predecessor: Zero?
) : BitComparable {

    override fun hashCode() = long.hashCode()
    override fun equals(other: Any?) = long == other
    override fun toString() = long.toString()

    final override val natural : NaturalComparable =
        LongNaturalComparable(long)


}
object BitZero : Bit(
    BitZero, 0,
    DigitZero,
    Zero,
    BitOne,
    null
){

    override fun compareTo(other: BitComparable) = when (other.bit){
        BitZero -> 0
        BitOne -> -1
    }

}


object BitOne : Bit(
    BitOne, 1,
    DigitOne, null,
    DigitTwo,
    Zero){

    override fun compareTo(other: BitComparable) = when (other.bit){
        BitZero -> 1
        BitOne -> 0
    }
}
