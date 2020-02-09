package nl.janvanrosmalen.human.natural

val Int.natural : Natural? get() = if (this>=0) LongNatural(toLong()) else null

val Enum<*>.naturalOrdinal get() : Natural = LongNatural(ordinal.toLong())