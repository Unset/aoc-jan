package xpair

import nl.janvanrosmalen.natural.Equal
import nl.janvanrosmalen.natural.Greater
import nl.janvanrosmalen.natural.Less
import nl.janvanrosmalen.natural.compare

fun <T> List<T>.chain() : List<Duo<T>> = when (size){
        0,1 -> emptyList()
        else ->
            emptyList<Duo<T>>().toMutableList().also {
                var left = this.first()
                for (right in drop(1)){
                    it.add(left duo right)
                    left = right
                }
            }
}


fun Int.getCombinations() : List<Duo<Int>> = when (this compare 1) {
    Less, Equal -> emptyList<Duo<Int>>()
    Greater -> (this-1).getCombinations() + (0 until (this-1)).map {it duo this-1}
}

fun <T> Iterable<T>.reduceTwo(operation : (Duo<T>) -> T): T {
    return this.reduce{acc, t -> operation(acc duo t)}
}

