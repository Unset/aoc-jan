package nl.janvanrosmalen.batshit

import xpair.letFirst

fun Byte.toPositiveInt() = toInt() and 0xFF

fun Byte.toPositiveLong() = toLong() and 0xFF

fun ReadCursor<Byte>.takePositiveInt() = take().letFirst { it.toPositiveInt() }

fun ReadCursor<Byte>.takePositiveLong() = take().letFirst { it.toPositiveLong() }