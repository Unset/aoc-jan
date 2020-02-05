package nl.janvanrosmalen.human

interface Digit : Comparable<Digit>, XDigitable {

    val predecessor : Digit?

    val successor : Digit?

}