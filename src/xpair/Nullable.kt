package xpair

fun <T> T.unless(annihilator : (T) -> Boolean) : T? = if (annihilator(this)) null else this