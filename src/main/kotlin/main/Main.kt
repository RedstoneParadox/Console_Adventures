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

val instructions: String = "Type [attack {enemy name}] to attack and [block] to block."
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

    Console.listen(Console.START_COMMAND)
    phase = Phase.INSTRUCTIONS
}

private fun instructions() {
    Console.send(instructions)
    Console.send(instructions2)

    Console.listen(Console.START_COMMAND)
    phase = Phase.GAME
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
    Console.listen(Console.CONTINUE_COMMAND)

    if (gamePhase == GamePhase.COMBAT) {
        combatEncounter()
    }
}

private fun death() {
    Console.send("You died. Type [retry] to play again")
    Console.listen(Console.RETRY_COMMAND)
    phase = Phase.GAME
    GameData.player.reset()
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