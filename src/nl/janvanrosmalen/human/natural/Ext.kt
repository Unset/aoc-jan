package nl.janvanrosmalen.human.natural

val Int.natural : NaturalComparable? get() = if (this>=0) LongNaturalComparable(toLong()) else null


val Long.natural : NaturalComparable? get() = if (this>=0) LongNaturalComparable(this) else null

val Enum<*>.naturalOrdinal get() : NaturalComparable = LongNaturalComparable(ordinal.toLong())