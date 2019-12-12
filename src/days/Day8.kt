package days

import xpair.*

class Day8 : Day(8) {

    val imageWidth = 25
    val imageHeight = 6

    val image by lazy {
        inputString.chunked(imageWidth * imageHeight)
    }

    override fun partOne(): String {
        val lowestZeroLayer = image.minBy {layer -> layer.countPixel('0')}!!

        val product = lowestZeroLayer.let {it.countPixel('1') * it.countPixel('2')}

        return "$product"
    }

    fun String.countPixel(pixel : Char) = this.count {it == pixel}

    override fun partTwo(): String {

        val result = image.reduceTwo {
            println(it)
            it.toCharList().zip().map {pair -> pair.calculateForeground() }.joinToString ("")
        }

        result.replace('0', ' ').chunked(imageWidth).forEach {println(it)}

        return ""
    }

    fun Two<Char>.calculateForeground() : Char{
        return when (first){
            '0' -> '0'
            '1' -> '1'
            '2' -> second
            else -> throw Exception(first.toString())
        }


    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day8().go()
        }
    }



}
