package main

import main.console.Console
import main.encounter.Encounter
import main.pool.WeightedPools
import main.util.GameData

/**
 * Created by RedstoneParadox on 12/22/2018.
 */

/**
 * Created by RedstoneParadox on 12/22/2018.
 */
var run = true
var phase: Phase = Phase.MAIN_MENU
lateinit var gamePhase : GamePhase

val greeting: String = "Welcome to Console Adventures!"
val greeting2: String = "Type [start] to begin."

val instructions: String = "Type [attack] to attack and [block] to block."
val instructions2: String = "Type [start] whenever you are ready."


fun main(args: Array<String>) {
    WeightedPools.initPools()
    Console.initCommands()
    mainMenu()

    while (run) {
        if (phase == Phase.MAIN_MENU) {
            mainMenu()
        }
        else if (phase == Phase.INSTRUCTIONS) {
            instructions()
        }
        else if (phase == Phase.GAME) {
            game()
        }
        else if (phase == Phase.PLAY_AGAIN) {
            death()
        }
    }
}

private fun mainMenu() {
    Console.send(greeting)
    Console.send(greeting2)

    while (phase == Phase.MAIN_MENU) {
        var input : String? = readLine()

        if (input == "start") {
            phase = Phase.INSTRUCTIONS
            break
        }
    }
}

private fun instructions() {
    Console.send(instructions)
    Console.send(instructions2)

    while (phase == Phase.INSTRUCTIONS) {
        var input : String? = readLine()

        if (input == "start") {
            phase = Phase.GAME
            break
        }
    }
}

private fun game() {
    gamePhase = GamePhase.COMBAT

    while (phase == Phase.GAME) {

        if (gamePhase == GamePhase.COMBAT) {
            combatEncounter()
        }
    }
}

private fun combatEncounter() {
    GameData.encounter = Encounter()
    GameData.encounter.run()

    if (GameData.player.isDead()) {
        gamePhase = GamePhase.COMPLETE
        phase = Phase.PLAY_AGAIN
    }
    else {
        GameData.player.health = GameData.player.maxHealth

        if (GameData.player.hasPoints()) {
            GameData.player.chooseSkill()
        }
    }

    Console.send("Type [continue] to continue")
    while (true) {
        val input : String? = readLine()

        if (input == "continue") {
            break
        }
    }

    if (gamePhase == GamePhase.COMBAT) {
        combatEncounter()
    }
}

private fun death() {
    Console.send("You died. Type [retry] to play again")

    while (true) {
        val input : String? = readLine()

        if (input == "retry") {
            phase = Phase.GAME
            GameData.player.health = GameData.player.maxHealth
            break
        }
    }
}

enum class Phase {
    MAIN_MENU,
    INSTRUCTIONS,
    GAME,
    PLAY_AGAIN
}

enum class GamePhase {
    COMBAT,
    COMPLETE
}