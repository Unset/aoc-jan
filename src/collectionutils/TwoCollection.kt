package collectionutils

interface TwoCollection<out E> : Collection<E> {

    operator fun component1() : E
    operator fun component2() : E

    fun toList() : TwoList<out E> {
        return TwoList(component1(), component2())
    }
}