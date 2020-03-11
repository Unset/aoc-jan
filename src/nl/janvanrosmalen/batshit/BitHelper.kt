package nl.janvanrosmalen.batshit

fun Byte.toPositiveInt() = toInt() and 0xFF

fun Byte.toPositiveLong() = toLong() and 0xFF