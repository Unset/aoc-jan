package baseten

import xpair.both
import xpair.nullLowComparison
import xpair.phair

interface Natural : Comparable<Natural> {
    val positive : Positive?

    override fun compareTo(other: Natural) : Int {
        return phair(other).both { it.positive }.nullLowComparison.value
    }
}


object Zero : Natural{
    override val positive: Positive?
        get() = null
}

class LongNatural(original : Long) : Natural{
    override val positive: Positive? = if (original == 0L ) null else LongPositive(original)

    override fun compareTo(other: Natural) : Int {
        return phair(other).both { it.positive }.nullLowComparison.value
    }
}

class LongPositive(original : Long) : Positive{
    override val digits: List<Digit> = original.toString().toDigits()

}


fun Long.natural() = LongNatural(this)