package xpair

interface Pairlike<out A, out B>{

    val first : A
    val second : B

}

fun <A,B>Pairlike<A,B>.toPair() : Pair<A, B> = first to second

interface Compair<A, B> : Pairlike<A,B>, Comparable<Compair<A,B>>{

}

open class CompairImpl<A : Comparable<A>, B : Comparable<B>>(override val first: A, override val second: B) : Compair<A,B> {
    override fun compareTo(other: Compair<A, B>): Int {
        return when (this.first compare other.first){
            Greater -> Greater
            Less -> Less
            Equal -> this.second compare other.second
        }.value
    }
}


fun <A : Comparable<A>, B : Comparable<B>> Pair<A,B>.toCompair() : Compair<A, B> = CompairImpl(first, second)