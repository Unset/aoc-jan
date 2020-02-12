package nl.janvanrosmalen.human.natural

interface Naturable {
    val natural : NaturalComparable

    val zero : Zero?

    val bit : Bit?

    val digit : Digit?

    val long : Long?

    val predecessor : Naturable?
}