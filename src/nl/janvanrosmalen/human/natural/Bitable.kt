package nl.janvanrosmalen.human.natural

interface Bitable : Digitable {
    override val bit : Bit

    override val predecessor : Zero?

    override val successor10 : Digitable

}