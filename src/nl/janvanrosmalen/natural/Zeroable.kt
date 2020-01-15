package nl.janvanrosmalen.natural

interface Zeroable : Bitable {
    override val zero : Zero get() = Zero
    override val bit: Bit get() = BitZero
    override val naturalInt: NaturalInt get() = Zero
    override val naturalLong: NaturalLong get() = Zero

}