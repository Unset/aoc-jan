package days

import december.MachineState

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
}


