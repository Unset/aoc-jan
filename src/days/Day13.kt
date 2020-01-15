package days

import collectionutils.Pos
import collectionutils.Tape
import collectionutils.drawWorld
import collectionutils.setValue
import december.BigMachine
import nl.janvanrosmalen.compare.Equal
import nl.janvanrosmalen.compare.Greater
import nl.janvanrosmalen.compare.Less
import nl.janvanrosmalen.compare.compare
import xpair.*
import java.lang.Thread.sleep

class Day13 : Day(13) {

    val tape by lazy { Tape.fromString(inputString)}

    val newGame by lazy { VideoGame(BigMachine(tape)) }

    override fun partOne(): String {

        var game = newGame

            while (!game.machine.done){
                game = game.step()
            }

        game.world.drawWorld { it.ascii }



        return game.world.count {entry -> entry.value == GameTile.BLOCK}.toString()
    }

    override fun partTwo(): String {
        var game = newGame.insertCoin()

        var joyStick = JoyStick.CENTER

        var hold = 0

        var prevBall : Pos? = null

        while (!game.machine.done){
            game = game.step(joyStick)
            hold--
            if (hold<=0 && game.world.isValid() && game.ball != prevBall){
                game.draw()
                joyStick = when(game.ball?.first?.compare(game.paddleX)){
                    null, Equal -> JoyStick.CENTER
                    Greater -> JoyStick.RIGHT
                    Less -> JoyStick.LEFT
                }
                println(joyStick)
                sleep(10)

            }
            game.ball?.let{prevBall = it}
        }

        return game.score.toString()
    }

    fun Map<Pos, GameTile>.isValid() : Boolean = listOf(GameTile.PADDLE, GameTile.BALL).all {containsValue(it)}

    fun getJoystick() : JoyStick {
        return when (readLine()?.toUpperCase()){
            "A" -> JoyStick.LEFT
            "D" -> JoyStick.RIGHT
            else -> JoyStick.CENTER
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            Day13().go()
        }
    }
}

data class VideoGame(val machine : BigMachine, val joyStick : JoyStick = JoyStick.CENTER, val world : Map<Pos, GameTile> = mapOf(), val score : Int = -1){

    fun insertCoin() = copy (
        machine = machine.copy (
            tape = machine.tape.setIndex(0.toBigInteger(), 2.toBigInteger())


        )
    )

    val ball by lazy {world.entries.firstOrNull {it.value == GameTile.BALL}?.key}


    val paddleX by lazy {world.entries.firstOrNull {it.value == GameTile.PADDLE}?.key?.first ?: 0}

    fun step(joy : JoyStick = JoyStick.CENTER) : VideoGame {
        var newWorld = world
        var newScore = score

        val (newMachine, trio) = machine.copy(input = listOf(joy.direction.toBigInteger())).runTillThreeOutput()

        trio?.allThree {it.toInt()}?.let {
            when (it.firstAndSecond) {
                Pos(-1, 0) -> newScore = it.third
                else -> newWorld = world.setValue(it.firstAndSecond, GameTile.values()[it.third])
            }
        }
        return copy(
            machine = newMachine,
            world = newWorld,
            score = newScore
        )

    }

    fun draw(){
        world.drawWorld { it.ascii }
        println("SCORE: $score PTS ")
    }

}


enum class JoyStick(val direction : Int){
    LEFT(-1),
    CENTER(0),
    RIGHT(1)
}

enum class GameTile(val ascii : String) {
    EMPTY("."),
    WALL("#"),
    BLOCK("$"),
    PADDLE("="),
    BALL("O")
}


