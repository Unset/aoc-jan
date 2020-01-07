package baseten

import xpair.*

interface Positive : Comparable<Positive> {
    val digits : List<Digit>

    override fun compareTo(other: Positive): Int  {
            val digits = phair(other).both { it.digits }
            return digits.both{it.size}.comparison.tieBreak {
                digits.zip().fold(Equal as Comparison, {acc, pair -> acc.tieBreak { pair.comparison } })
            }.value
        }
}

object One : Positive {
    override val digits = listOf(Digit.ONE)
}
