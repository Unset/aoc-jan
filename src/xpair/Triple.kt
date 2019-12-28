package xpair

fun <A,B,C> List<Triple<A,B,C>>.unzip() = Triple(
    map {it.first},
    map {it.second},
    map {it.third}
)