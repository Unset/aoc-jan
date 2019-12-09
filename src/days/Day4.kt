package days


import collectionutils.*
import xpair.*
import java.lang.Exception
import kotlin.math.abs
import kotlin.math.absoluteValue


class Day4 : Day(4) {

    val inputRange by lazy {inputString.split('-').toTwo().both { it.toInt() }.toRange().map{it.toString()}}


    override fun partOne(): String {

        val validCodes = inputRange.filter { s ->
            s.toList().chain().all {it.first <= it.second} && (0..9).any {s.contains("$it$it")}
        }

        return validCodes.size.toString()
    }



    override fun partTwo(): String {
        val validCodes = inputRange.filter { s ->
            s.toMutableList().chain().all {it.first <= it.second}
                    && (0..9).any {s.contains("$it$it") && !s.contains("$it$it$it")} // left is required, right is forbidden
        }

        return validCodes.size.toString()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day4().go()
        }
    }

}




