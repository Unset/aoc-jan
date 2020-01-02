package december

import collectionutils.Tape
import collectionutils.setIndex
import xpair.Trio
import xpair.toTrio
import java.math.BigInteger

data class BigMachine(val tape : Tape, val position : BigInteger = BigInteger.ZERO, val base : BigInteger = BigInteger.ZERO, val done : Boolean = false, val input : List<BigInteger> = emptyList(), val output : List<BigInteger> = emptyList()) {

    fun runTillDone() : BigMachine{
        var machine = this
        var counter = 0
        println("Running")
        while (!machine.done){
            machine = machine.next()
            counter++
            if (counter%1000==0) print(".")
            if (counter%100000==0) println("")
        }
        println("!")
        println("Done!")
        return machine
    }

    fun runTillOutput() : Pair<BigMachine, BigInteger?>{
        var machine = this.copy(output = emptyList())
        while (true){
            val outputVal = machine.output.firstOrNull()
            if (machine.done || outputVal != null) return Pair(machine, outputVal)
            machine = machine.next()
        }
    }

    fun runTillTwoOutput() : BigMachine{
        var machine = this.copy(output = emptyList())
        while (true){
            if (machine.done || machine.output.size == 2) return machine
            machine = machine.next()
        }
    }

    fun runTillThreeOutput() : Pair<BigMachine, Trio<BigInteger>?>{
        var machine = this.copy(output = emptyList())
        while (true){
            if (machine.done) return Pair(machine, null)
            if (machine.output.size == 3) return Pair(machine, machine.output.toTrio())
            machine = machine.next()
        }
    }


    val opcode : Int
        get() = tape[position].toInt() % 100

    val flags : List<Int>
        get() = "0000${tape[position]}".dropLast(2).map {"$it".toInt()}.reversed()

    val firstArg : BigInteger
        get() = tape[position + BigInteger.ONE]

    fun jumpIf(condition : (BigInteger) -> Boolean): BigMachine{
        return copy(
            position = if (condition(getArgument(0))) getArgument(1) else position + 3.toBigInteger()
            )
    }


    fun next() : BigMachine {
        return when (opcode){
            1 -> applyMath {a, b -> a + b}
            2 -> applyMath {a, b -> a * b}
            5 -> jumpIf {it!= BigInteger.ZERO}
            6 -> jumpIf {it== BigInteger.ZERO}
            7 -> applyMath {a, b -> if (a<b) BigInteger.ONE else BigInteger.ZERO}
            8 -> applyMath {a, b -> if (a==b) BigInteger.ONE else BigInteger.ZERO}

            9 -> copy(
                base = base + getArgument(0),
                position = position + 2.toBigInteger()
            )

            3 -> copy(
                tape = tape.setIndex(getTarget(0), input.first()),
                position = position + 2.toBigInteger(),
                input = input.drop(1))
            4 -> copy(
                position = position + 2.toBigInteger(),
                output = output + listOf(getArgument(0)))
            99 -> copy(
                position = position + 1.toBigInteger(),
                done = true)
            else -> throw Exception("Unknown opcode $opcode")
        }
    }

    fun getArgument(argnum : Int) : BigInteger{
        val value = tape[position + BigInteger.ONE + argnum.toBigInteger()]
        return when (val flag = flags[argnum]) {
            0 -> tape[value]
            1 -> value
            2 -> tape[base + value]
            else -> throw Exception("Unknown flag $flag")
        }
    }

    fun getTarget(argnum : Int) : BigInteger{
        val value = tape[position + BigInteger.ONE + argnum.toBigInteger()]
        return when (val flag = flags[argnum]) {
            0 -> value
            1 -> throw Exception("No direct target allowed")
            2 -> base + value
            else -> throw Exception("Unknown flag $flag")
        }
    }

    fun applyMath(operation : (BigInteger, BigInteger) -> BigInteger) : BigMachine{
        return copy(
            position = position + 4.toBigInteger(),
            tape =
            operation(getArgument(0), getArgument(1))
                .let {
                    tape.setIndex(getTarget(2),it)
                }
        )
    }
}