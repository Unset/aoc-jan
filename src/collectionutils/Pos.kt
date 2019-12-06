package collectionutils

typealias Pos = TwoList<Int>

fun Pos.toRange() : IntRange {
    return sort().let {IntRange(it.left, it.right)}
}

operator fun Pos.contains(some : Int) : Boolean{
    return some in toRange()
}
