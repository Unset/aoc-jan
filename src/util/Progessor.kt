package util

import java.math.BigInteger


val base = 100.toBigInteger()

val rule = 100.toBigInteger()

fun progressor(count : BigInteger, someLine : ()->String? = {null}){



    if (count.mod(base).isZero()) print("X")
    if (count.mod(base * rule).isZero()) println("W${someLine()}")
}

fun BigInteger.isZero() = this == BigInteger.ZERO