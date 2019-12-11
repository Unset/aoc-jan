package xpair

fun <T> List<T>.chain() : List<Two<T>> = when (size){
        0,1 -> emptyList()
        else ->
            ArrayList<Two<T>>(size - 1).also {
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