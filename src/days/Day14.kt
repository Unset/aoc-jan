package days

import nl.janvanrosmalen.natural.Equal
import nl.janvanrosmalen.natural.Greater
import nl.janvanrosmalen.natural.Less
import nl.janvanrosmalen.natural.compare
import util.progressor
import xpair.*
import java.math.BigInteger

class Day14 : Day(14) {

    val reactions by lazy {inputList.map {it.toReaction()}.toMap()}

    val trillion by lazy {"1000000000000".toBigInteger()}

    override fun partOne(): String {

        var neededChemicals = mutableMapOf("FUEL" to BigInteger.ONE)
        var stock = mutableMapOf<String, BigInteger>()

        processAll(neededChemicals, stock)


        return "It is ${neededChemicals.getOrZero("ORE")}"
    }



    override fun partTwo(): String {

        var stock = mutableMapOf<String, BigInteger>()

        var needed = mutableMapOf<String, BigInteger>()

        var fuelProduced = BigInteger.ZERO

        while (true){
            needed["FUEL"] = needed.getOrZero("FUEL") + BigInteger.ONE
            processAll(needed, stock)
            val ores = needed.getOrZero("ORE")
            if (ores > trillion){
                break;

            }
            fuelProduced++
            progressor(fuelProduced) {ores.toString()}
        }



        return "$fuelProduced"
    }


    fun processAll(neededChemicals : MutableMap<String,BigInteger>, stock : MutableMap<String,BigInteger>){

        while (neededChemicals.notJustOre()){
            val (chemical, needed) = neededChemicals.firstNonOre()
            val inStock = stock.getOrZero(chemical)



            when (inStock compare needed){
                Greater, Equal -> {
                    neededChemicals.remove(chemical)
                    stock[chemical] = inStock - needed
                }
                Less -> {
                    val (produced, ingredients) = reactions[chemical]!!

                    val shortage = needed - inStock


                    val multiplier = (shortage / produced).max(BigInteger.ONE)

                    stock[chemical] = inStock + produced * multiplier
                    neededChemicals.addIngredients(ingredients, multiplier)
                }
            }
        }

    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day14().go()
        }
    }
}

fun MutableMap<String, BigInteger>.addIngredients(ingredients : Map<String, BigInteger>, multiplier : BigInteger){
    ingredients.forEach{(ingredientChemical, ingredientNeeded)->
        this[ingredientChemical] = this.getOrZero(ingredientChemical) + ingredientNeeded * multiplier
    }
}

fun <E> Map<E, BigInteger>.getOrZero(index : E) = getOrDefault(index, BigInteger.ZERO)

fun <E> Map<E, BigInteger>.firstNonOre() = entries.first {it.value != BigInteger.ZERO && it.key != "ORE"}

fun <E> Map<E, BigInteger>.notJustOre() = entries.any {it.value != BigInteger.ZERO && it.key != "ORE" }

fun String.toReaction() : Pair<String, Pair<BigInteger, Map<String, BigInteger>>> {
    val (left, right) = this.split("=>").toDuo()
    val ingredients = left.split(",").map { it.toChemicalQuantity() }.toMap()
    val product = right.toChemicalQuantity()
    return Pair(product.first, Pair(product.second, ingredients))
}

fun String.toChemicalQuantity() : Pair<String, BigInteger> = this.trim().split(" ").toDuo().letFirst {it.toBigInteger()}.swap()

