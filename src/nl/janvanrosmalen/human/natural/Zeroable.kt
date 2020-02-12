package nl.janvanrosmalen.human.natural

interface Zeroable : Bitable {
    override val zero : Zero

    override val predecessor : Nothing? get() = null

}