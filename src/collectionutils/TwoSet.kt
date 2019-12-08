package collectionutils

class TwoSet<E> private constructor(private val list : TwoList<E>) : TwoCollection<E>, Set<E> {
    override fun component1() = list.left
    override fun component2() = list.right

    override fun toList() = list

    override val size = 2

    override fun contains(element: E) = list.contains(element)

    override fun containsAll(elements: Collection<E>) = list.containsAll(elements)

    override fun isEmpty() = false

    override fun iterator() = list.iterator()

    companion object {

        fun <E>create(source : TwoList<E>) : TwoSet<E>? = if (source.left != source.right) TwoSet(source) else null

    }
}