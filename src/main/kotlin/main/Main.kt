package main

import main.characters.PlayerCharacter
import main.encounter.Encounter
import main.pool.WeightedPools

/**
 * Created by RedstoneParadox on 12/22/2018.
 */

/**
 * Created by RedstoneParadox on 12/22/2018.
 */
var run = true
var phase: Phase = Phase.MAIN_MENU
lateinit var gamePhase : GamePhase

val player: PlayerCharacter = PlayerCharacter(10, "you")

val greeting: String = "Welcome to Console Adventures!"
val greeting2: String = "Type [start] to begin."

val instructions: String = "Type [attack] to attack and [block] to block."
val instructions2: String = "Type [start] whenever you are ready."


fun main(args: Array<String>) {
    WeightedPools.initPools()
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
    println(greeting)
    println(greeting2)

    while (phase == Phase.MAIN_MENU) {
        var input : String? = readLine()

        if (input == "start") {
            phase = Phase.INSTRUCTIONS
            break
        }
    }
}

private fun instructions() {
    println(instructions)
    println(instructions2)

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
    Encounter(player).run()
    if (player.isDead()) {
        gamePhase = GamePhase.COMPLETE
        phase = Phase.PLAY_AGAIN
    }
    else {
        player.health = player.maxHealth

        if (player.hasPoints()) {
            player.chooseSkill()
        }
    }

    println("Type [continue] to continue")
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
    println("You died. Type [retry] to play again")

    while (true) {
        val input : String? = readLine()

        if (input == "retry") {
            phase = Phase.GAME
            player.health = player.maxHealth
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