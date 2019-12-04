package days

import whenever.*

class Day2 : Day(2) {


    val inputTape : List<Int> by lazy {inputString.split(',').map {it.toInt()}}

    override fun partOne(): String {

        var machine = make(12, 2).runTillDone()

        return machine.tape[0].toString()
    }

    override fun partTwo(): String {
        for(noun in 0..99){
            for(verb in 0..99){
                var machine = make(noun, verb).runTillDone()
                if(machine.tape[0] == 19690720){
                    return (100 * noun + verb).toString()
                }
            }
        }
        return "not found"
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day2().go()
        }
    }

    fun make(noun : Int, verb : Int) : MachineState{
        return MachineState(inputTape.toMutableList().apply {
            this[1] = noun
            this[2] = verb
        })
    }

    data class MachineState(val tape : List<Int>, val position : Int = 0, val done : Boolean = false) {

        fun runTillDone() : MachineState{
            var machine = MachineState(tape)
            while (!machine.done){
                machine = machine.next()
            }
            return machine
        }

        val opcode : Int
            get() = tape[position]

        val arguments : List<Int>
            get() = listOf(tape[position+1],tape[position+2])

        fun next() : MachineState {
            return when (val opcode = tape[position]){
                1 -> applyMath {a, b -> a + b}
                2 -> applyMath {a, b -> a * b}
                99 -> {
                    MachineState(tape, position, true)
                }
                else -> throw Exception("Unknown opcode $opcode")
            }
        }

        fun getArgument(argnum : Int) : Int{
            return tape[position+argnum]
        }

        fun applyMath(operation : (Int, Int) -> Int) : MachineState{
            return MachineState(
                position = position + 4,
                tape =
                operation(tape[getArgument(1)], tape[getArgument(2)])
                    .let {
                        tape.toMutableList().apply {this[getArgument(3)] = it}
                    }
            )
        }


    }
}


