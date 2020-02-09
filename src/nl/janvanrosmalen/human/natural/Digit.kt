package nl.janvanrosmalen.human.natural

interface Digit : Comparable<Digit>,
    XDigitable {

    val predecessor : Digit?

    val successor : Digit?

}