package main.console

import main.util.GameData

/**
 * Created by RedstoneParadox on 12/27/2018.
 */
class EnemyNameArgument(argumentType: ArgumentType, name: String) : AbstractArgument(argumentType, name) {

    override fun getExpectedValues(argument: Any): Boolean {
        for (enemy in GameData.encounter.enemies) {
            if (enemy.name == argument) {
                return true
            }
        }

        return false
    }
}