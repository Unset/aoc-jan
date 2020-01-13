package baseten

import nl.janvanrosmalen.natural.Digit

fun String.toDigits() : List<Digit> {
    return map {it.onlyDigit()}
}

fun String.onlyDigit() = Digit.values()[toInt()]


fun Char.onlyDigit() = Digit.values()[toString().toInt()]

fun List<Digit>.toInt() = joinToString("").toInt()
