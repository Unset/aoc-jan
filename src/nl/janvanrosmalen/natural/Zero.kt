package nl.janvanrosmalen.natural

object Zero : Comparable<Zero> {
    override fun compareTo(other: Zero) = 0
    const val int = 0
}