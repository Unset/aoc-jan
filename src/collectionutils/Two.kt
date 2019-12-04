package collectionutils

import whenever.Negative
import whenever.Positive
import whenever.Zero
import whenever.split

typealias Pos = Two<Int>

fun Pos.toRange() : IntRange {
    return sort().let {IntRange(it.left, it.right)}
}

operator fun Pos.contains(some : Int) : Boolean{
    return some in toRange()
}

fun <T : Comparable<T>> Two<T>.sort() : Two<T> {
    return when (left.compareTo(right).split()){
        Positive -> swap()
        Zero, Negative -> this
    }
}

/**
 * This is quite ugly, but hey!
 */
fun <T> List<T>.chain() : MutableList<Two<T>> {
    val result = emptyList<Two<T>>().toMutableList()
    if (this.size <= 1) return result

    var left = this.first()

    for (right in this.drop(1)){
        result.add(Two(left, right))
        left = right
    }
    return result
}


fun <T> Iterable<T>.toTwo() : Two<T> {
    val total = this.toList()
    return Two(total[0], total[1])
}

data class Two<T>(val left : T, val right : T) {

    fun <R> map(transform : (T) -> R) : Two<R> {
        return Two(transform(left), transform(right))
    }

    fun andSwapped() : Two<Two<T>>{
        return Two(this, this.swap())
    }

    fun toList() : List<T> {
        return listOf(left, right)
    }

    operator fun invoke(side: Side) : T{
        return when(side){
            Left -> left
            Right -> right
        }
    }

    fun swap() : Two<T> {
        return Two(right, left)
    }

    fun set(side : Side, value : T) : Two<T>{
        return when(side){
            Left -> setLeft(value)
            Right -> setRight(value)
        }
    }

    fun setLeft(value : T) : Two<T>{
        return Two(value, right)
    }

    fun setRight(value : T) : Two<T>{
        return Two(left, value)
    }

    fun setLeft(value : Two<T>.() -> T) : Two<T>{
        return Two(value(), right)
    }

    fun setRight(value : Two<T>.() -> T) : Two<T>{
        return Two(left, value())
    }






}