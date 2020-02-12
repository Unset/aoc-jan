package nl.janvanrosmalen.human.natural

interface Digitable : Naturable, Longable {
    override val digit : Digit

    override val predecessor : Digitable?

    val successor10 : Digitable?

}