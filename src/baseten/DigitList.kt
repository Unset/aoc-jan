package baseten

import xpair.Equal
import xpair.Greater
import xpair.Less
import xpair.compare

fun String.toDigits() : List<Digit> {
    return map {it.onlyDigit()}
}

fun String.onlyDigit() = Digit.values()[toInt()]


fun Char.onlyDigit() = Digit.values()[toString().toInt()]

fun List<Digit>.toInt() = joinToString("").toInt()
