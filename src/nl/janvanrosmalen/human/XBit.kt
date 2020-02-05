package nl.janvanrosmalen.human

sealed class XBit(
    final override val xBit : XBit,
    final override val long : Long,
    final override val xDigit : XDigit
) : Bit {

    override fun hashCode() = long.hashCode()
    override fun equals(other: Any?) = long == other
    override fun toString() = long.toString()

    final override val digit = xDigit
    final override val bit = xBit


}
object BitZero : XBit(BitZero, 0, DigitZero){

    override fun compareTo(other: Bit) = when (other.xBit){
        BitZero -> 0
        BitOne -> -1
    }

}


object BitOne : XBit(BitOne, 1, DigitOne){

    override fun compareTo(other: Bit) = when (other.xBit){
        BitZero -> 1
        BitOne -> 0
    }
}
