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
val greeting2: String = "Type [start] to begin or [help] for commands."


fun main(args: Array<String>) {
    WeightedPools.initPools()
    Console.initCommands()

    Console.send(greeting)
    Console.send(greeting2)

    while (run) {
        if (phase == Phase.MAIN_MENU) {
            mainMenu()
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

    while (true) {
        var input = Console.listen(Console.START_COMMAND, Console.HELP_COMMAND)

        if (input[0] == "start") {
            phase = Phase.GAME
            break
        }
        else if (input[0] == "help") {
            Console.printCommands()
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
    GAME,
    PLAY_AGAIN
}

enum class GamePhase {
    COMBAT,
    COMPLETE
}