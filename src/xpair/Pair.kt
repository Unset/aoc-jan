package xpair


fun <A, B> Pair<A, B>.swap() = Pair(second, first)

fun <A, B> Pair<A, B>.setFirst(value : A) = copy(first = value)

fun <A, B> Pair<A, B>.setSecond(value : B) = copy(second = value)

fun <A, B> Pair<List<A>,List<B>>.zip() = first zip second



