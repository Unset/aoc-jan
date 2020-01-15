package nl.janvanrosmalen.natural

interface Bitable : Digitable {
    override val bit : Bit

    override val digit: Digit
        get() = bit.digit

}