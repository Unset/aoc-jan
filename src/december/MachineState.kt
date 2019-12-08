package december

import collectionutils.setIndex

data class MachineState(val tape : List<Int>, val position : Int = 0, val done : Boolean = false, val input : List<Int> = emptyList(), val output : List<Int> = emptyList()) {

    fun runTillDone() : MachineState{
        var machine = this
        while (!machine.done){
            println(machine)
            machine = machine.next()
        }
        return machine
    }

    val opcode : Int
        get() = tape[position] % 100

    val flags : List<Int>
        get() = "0000${tape[position]}".dropLast(2).map {"$it".toInt()}.reversed()

    val firstArg : Int
        get() = tape[position + 1]

    fun jumpIf(condition : (Int) -> Boolean): MachineState{
        return MachineState(
            tape = tape,
            position = if (condition(getArgument(0))) getArgument(1) else position + 3,
            input = input,
            output = output)
    }


    fun next() : MachineState {
        println("At ${position} Apply op ${tape.getOrNull(position)} ( ${tape.getOrNull(position+1)}, ${tape.getOrNull(position+1)} ) is flags : $flags")
        return when (opcode){
            1 -> applyMath {a, b -> a + b}
            2 -> applyMath {a, b -> a * b}
            5 -> jumpIf {it!=0}
            6 -> jumpIf {it==0}
            7 -> applyMath {a, b -> if (a<b) 1 else 0}
            8 -> applyMath {a, b -> if (a==b) 1 else 0}


            3 -> MachineState(
                tape = tape.setIndex(firstArg, input.first()),
                position = position + 2,
                input = input.drop(1),
                output = output)
            4 -> MachineState(
                tape = tape,
                position = position + 2,
                input = input,
                output = output + listOf(tape[firstArg]))
            99 -> MachineState(
                tape = tape,
                position = position + 1,
                done = true,
                input = input,
                output = output)
            else -> throw Exception("Unknown opcode $opcode")
        }
    }

    fun getArgument(argnum : Int) : Int{
        val value = tape[position + 1 + argnum]
        return if (flags[argnum]==1) value else tape[value]

    }

    fun applyMath(operation : (Int, Int) -> Int) : MachineState{
        return MachineState(
            input = input,
            output = output,
            position = position + 4,
            tape =
            operation(getArgument(0), getArgument(1))
                .let {
                    tape.setIndex(tape[position+3],it)
                }
        )
    }
}