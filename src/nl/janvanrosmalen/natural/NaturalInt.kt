package nl.janvanrosmalen.natural

interface NaturalInt : NaturalLong {
    val int : Int
    override val long: Long get() = int.toLong()
    override val naturalInt : NaturalInt get() = this
}