package collectionutils

sealed class Side {
    abstract val other : Side
}

object Left : Side(){
    override val other: Side
        get() = Right
}

object Right : Side(){
    override val other: Side
        get() = Left
}