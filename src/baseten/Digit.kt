package baseten

enum class Digit {
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE;

    override fun toString(): String {
        return ordinal.toString()
    }

    fun toInt() = ordinal



}




