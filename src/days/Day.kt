package days

import util.InputReader

abstract class Day(val dayNumber: Int) {

    // lazy delegate ensures the property gets computed only on first access
    protected val inputList: List<String> by lazy { InputReader.getInputAsList(dayNumber) }
    protected val inputString: String by lazy { InputReader.getInputAsString(dayNumber) }

    abstract fun partOne(): String

    abstract fun partTwo(): String

    fun go() {
        println("== day ${dayNumber} ==")
        println(partOne())
        println(partTwo())
        println("=====")
    }
}
