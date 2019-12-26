package xpair


fun <A, B> Pair<A, B>.swap() = Pair(second, first)

fun <A, B> Pair<List<A>,List<B>>.zip() = first zip second



