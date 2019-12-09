package days

import xpair.Equal
import xpair.Greater
import xpair.Less
import xpair.compareZero

class Day1 : Day(1) {

    override fun partOne(): String {
        return inputList.map {it.toInt() / 3 - 2}.sum().toString()
    }

    override fun partTwo(): String {
        return inputList.map {getFuel(it.toInt())}.sum().toString()
    }

    fun getFuel(m : Int) : Int{
        val f = m / 3 - 2
        return when(f.compareZero()){
            Less, Equal -> 0
            Greater -> f + getFuel(f)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day1().go()
        }
    }



}
