package nl.janvanrosmalen.natural

val Int.natural : NaturalInt? get() = if (this > 9) NonDigit(this) else digit

class NonDigit(override val int: Int) : NaturalInt{

    override val digit: Digit? = null

    override val bit: Bit? = null

    override val zero: Zero? = null

    override fun toString() = int.toString()

}

val Long.natural : NaturalLong? get() = if (this > Int.MAX_VALUE) NonIntLong(this) else this.toInt().natural

class NonIntLong(override val long: Long) : NaturalLong{

    override val digit: Digit? = null

    override val bit: Bit? = null

    override val zero: Zero? = null

    override fun toString() = long.toString()

    override val naturalInt : NaturalInt? = null

}