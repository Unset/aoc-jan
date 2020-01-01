package collectionutils

import java.math.BigInteger
import java.math.BigInteger.ZERO

data class Tape(val tape : Map<BigInteger, BigInteger>) {

    operator fun get(index : BigInteger) : BigInteger{
        return tape[index] ?: ZERO
    }

    fun setIndex(index : BigInteger, value: BigInteger) : Tape{
        return Tape(tape.toMutableMap().apply {this[index] = value})
    }

    companion object {
        fun fromString(inputString : String) = Tape(inputString.split(',').mapIndexed {index, s -> index.toBigInteger() to BigInteger(s) }.toMap())
    }

}