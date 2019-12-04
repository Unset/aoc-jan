package whenever

import javafx.geometry.Pos
import kotlin.math.absoluteValue

sealed class SplitInt() {

    abstract val value : Int

    override fun toString(): String {
        return value.toString()
    }
}

object Zero : SplitInt() {
    override val value = 0
}

object Positive : SplitInt() {
    override val value = 1
}

object Negative : SplitInt() {
    override val value = -1
}




fun fromInt(i : Int) : SplitInt{
    return when {
        i>0 -> Positive
        i<0 -> Negative
        else -> Zero
    }
}

fun String.split() : SplitInt? {
    return this.toIntOrNull()?.split()
}


fun Int.split() : SplitInt {
    return fromInt(this)
}