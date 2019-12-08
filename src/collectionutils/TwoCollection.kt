package collectionutils

interface TwoCollection<E> : Collection<E> {

    operator fun component1() : E
    operator fun component2() : E

    fun toList() : TwoList<E> {
        return TwoList(component1(), component2())
    }
}