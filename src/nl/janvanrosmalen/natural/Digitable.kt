package nl.janvanrosmalen.natural

interface Digitable : NaturalInt{
    override val digit : Digit
    override val int : Int get() = digit.int
}