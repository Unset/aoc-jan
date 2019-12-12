package xpair

fun <T> List<T>.chain() : List<Two<T>> = when (size){
        0,1 -> emptyList()
        else ->
            emptyList<Two<T>>().toMutableList().also {
                var left = this.first()
                for (right in drop(1)){
                    it.add(left two right)
                    left = right
                }
            }
}



fun <E> Iterable<E>.onlyTwo() : Two<E> {
    val total = this.toList()
    return Two(total[0], total[1])
}

fun <T> Iterable<T>.reduceTwo(operation : (Two<T>) -> T): T {
    return this.reduce{acc, t -> operation(acc two t)}
}

