package xpair

import nl.janvanrosmalen.compare.Equal
import nl.janvanrosmalen.compare.Greater
import nl.janvanrosmalen.compare.Less
import nl.janvanrosmalen.compare.compare

interface PairAdapter<out A, out B>{

    operator fun component1() : A
    operator fun component2() : B

}

val <A,B>PairAdapter<A,B>.pair : Pair<A, B> get() = Pair(this.component1(), this.component2())

val <A>PairAdapter<A,*>.pairFirst : A get() = this.component1()

val <B>PairAdapter<*,B>.pairSecond : B get() = this.component2()


interface Compair<A, B> : PairAdapter<A,B>, Comparable<Compair<A,B>>{

}

private class CompairImpl<A : Comparable<A>, B : Comparable<B>>(private val first: A, private val second: B) : Compair<A,B> {
    override fun compareTo(other: Compair<A, B>): Int {
        return when (this.pairFirst compare other.pairFirst){
            Greater -> Greater
            Less -> Less
            Equal -> this.pairSecond compare other.pairSecond
        }.int
    }

    override fun component1() = first
    override fun component2() = second
}


fun <A : Comparable<A>, B : Comparable<B>> Pair<A,B>.adapt() : Compair<A, B> = CompairImpl(first, second)