package xpair


fun <A, B> Pair<A, B>.swap() = Pair(second, first)

fun <A, B> Pair<List<A>,List<B>>.zip() = first zip second

fun <A,B, X> Pair<A, B>.letFirst(transform : (A) -> X) = Pair(transform(first), second)

fun <A,B, X> Pair<A, B>.letSecond(transform : (B) -> X) = Pair(first, transform(second))

fun <A, B> A.phair(other : B) = Pair(this, other)

fun <A, B> Pair<A?, B?>.bothNull() = first == null && second == null