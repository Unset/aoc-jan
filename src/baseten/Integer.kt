package baseten
//
//sealed class Integer {
//    abstract val sign : Sign
//    abstract val absolute : Long
//}
//
//open class PositiveOrZero(val aboveZero: Long) : Integer() {
//    final override val sign = Plus
//    override val absolute: Long get() = aboveZero
//}
//
//open class Negative(val belowZero: Long) : Integer() {
//    final override val sign = Minus
//    override val absolute: Long get() = belowZero
//}
//
//sealed class Sign
//{
//    abstract val explicit : Char
//    abstract val human : String
//    abstract val plus : Boolean
//    abstract val minus : Boolean
//    abstract val flipped : Sign
//
//    override fun toString(): String {
//        return human
//    }
//
//
//}
//
//object Plus : Sign() {
//    override val explicit = '+'
//    override val human = ""
//    override val plus = true
//    override val minus = false
//    override val flipped = Minus
//}
//
//object Minus : Sign() {
//    override val explicit = '-'
//    override val human = "-"
//    override val plus = false
//    override val minus = true
//    override val flipped = Plus
//}