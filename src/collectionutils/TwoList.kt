package collectionutils

import whenever.*


fun <T : Comparable<T>> TwoList<T>.sort() : TwoList<T> {
    return when (left compare right){
        Greater -> swap()
        Equal, Less -> this
    }
}

/**
 * This is quite ugly, but hey!
 */
fun <T> List<T>.chain() : MutableList<TwoList<T>> {
    val result = emptyList<TwoList<T>>().toMutableList()
    if (this.size <= 1) return result

    var left = this.first()

    for (right in this.drop(1)){
        result.add(TwoList(left, right))
        left = right
    }
    return result
}


fun <T> Iterable<T>.toTwo() : TwoList<T> {
    val total = this.toList()
    return TwoList(total[0], total[1])
}

open class TwoList<T>(open val left : T, open val right : T) : List<T>, TwoCollection<T>{

    override operator fun component1() = left
    override operator fun component2() = right

    override fun toString() = "$left list $right"

    override fun equals(other: Any?) = if (other is TwoList<*>) left == other.left && right == other.right else false

    override fun hashCode(): Int {
        return left.hashCode() + right.hashCode() * 31
    }

    fun <R> map(transform : (T) -> R) : TwoList<R> {
        return TwoList(transform(left), transform(right))
    }

    fun andSwapped() : TwoList<TwoList<T>>{
        return TwoList(this, this.swap())
    }

    override fun toList() : TwoList<T> {
        return this
    }

    private fun toNormalList() : List<T> {
        return listOf(left, right)
    }

    operator fun invoke(side: Side) : T{
        return when(side){
            Left -> left
            Right -> right
        }
    }

    fun swap() : TwoList<T> {
        return TwoList(right, left)
    }

    fun set(side : Side, value : T) : TwoList<T>{
        return when(side){
            Left -> setLeft(value)
            Right -> setRight(value)
        }
    }

    fun setLeft(value : T) : TwoList<T>{
        return TwoList(value, right)
    }

    fun setRight(value : T) : TwoList<T>{
        return TwoList(left, value)
    }

    fun setLeft(value : TwoList<T>.() -> T) : TwoList<T>{
        return TwoList(value(), right)
    }

    fun setRight(value : TwoList<T>.() -> T) : TwoList<T>{
        return TwoList(left, value())
    }

    override val size = 2

    override fun contains(element: T) = left == element || right == element

    override fun containsAll(elements: Collection<T>) = elements.all {it in this}

    override fun get(index: Int): T = when(index){
        0 -> left
        1 -> right
        else -> throw IndexOutOfBoundsException()
    }

    override fun indexOf(element: T) = when(element) {
        left -> 0
        right -> 1
        else -> -1
    }

    override fun isEmpty() = false

    override fun iterator(): Iterator<T> = toNormalList().iterator()

    override fun lastIndexOf(element: T) = when(element) {
        right -> 1
        left -> 0
        else -> -1
    }

    override fun listIterator() = toNormalList().listIterator()

    override fun listIterator(index: Int) = toNormalList().listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int) = toNormalList().subList(fromIndex, toIndex)


}