package main.encounter

import main.Items.ShieldItem
import main.Items.WeaponItem
import main.characters.AbstractCharacter
import main.characters.GenericEnemy
import main.characters.PlayerCharacter
import main.console.Console
import main.pool.PoolFunctions
import main.pool.WeightedPools
import main.util.GameData
import kotlin.math.floor
import kotlin.random.Random

/**
 * Created by RedstoneParadox on 12/22/2018.
 */
class Encounter() {

    var enemies : ArrayList<GenericEnemy> = ArrayList()

    var stage : Stage = Stage.START

    fun addEnemy(enemyCharacter: GenericEnemy) {
        enemies.add(enemyCharacter)
    }

    fun run() {
        var enemyCount : Int = Random.nextInt(0, ((1 + floor(GameData.player.level.toDouble()/4)).toInt())) + 1
        var enemyNames : ArrayList<*> = WeightedPools.GENERIC_ENEMY_NAMES.loot(enemyCount)
        PoolFunctions.restorePool()

        for (i in 0 until(enemyCount)) {
            val enemy : GenericEnemy = GenericEnemy("generic enemy", 8, 10)
            enemy.name = enemyNames[i] as String
            addEnemy(enemy)
        }

        for (enemy in enemies) {
            val surpriseRoll : Int = Random.nextInt(1, 5)

            if (surpriseRoll == 1) {
                enemy.surpriseAttack(this)
            }
            else {
                if (enemy.customEntrance) {
                    Console.send(enemy.enter(false, null, 0))
                }
                else {
                    Console.send("A ${enemy.type} named ${enemy.name} approaches!")
                }
            }
        }

        while (!isOver()) {
            if (stage == Stage.START) {
                Console.send("You are at ${GameData.player.health} health.")
                stage = Stage.ONGOING
            }

            Console.send("It is your turn.")
            GameData.player.takeTurn()

            try {
                if (!enemies.isEmpty()) {
                    for (enemy in enemies) {
                        if (isOver()) {
                            break
                        }
                        enemy.takeTurn()
                    }
                }
            }
            catch (e : Exception) {

            }

            GameData.player.shieldItem = null

            if (!enemies.isEmpty()) {
                for (enemy in enemies) {
                    enemy.shieldItem = null
                }
            }

        }

    }

    fun attack(attacker : AbstractCharacter, target: AbstractCharacter, weapon: WeaponItem) {
        val damage : Int = (weapon.use(attacker) as Int) + attacker.strength

        val attackResult : AttackResult = target.takeDamage(damage)

        if (attackResult == AttackResult.HIT) {
            if (!target.isDead()) {
                Console.send("${attacker.type.capitalize()} attacked ${target.type} with ${attacker.refer("their")} ${weapon.name}, " +
                        "dealing $damage damage. ${target.type.capitalize()} ${target.refer("is")} at ${target.health} health.")
            }
            else {
                Console.send("${attacker.type.capitalize()} attacked ${target.type} with ${attacker.refer("their")} ${weapon.name}, " +
                        "dealing $damage damage and killing ${target.refer("them")}.")

                if (target is PlayerCharacter) {
                    enemies.clear()
                }
                else {
                    GameData.player.gainEXP((target as GenericEnemy).dropEXP())
                    enemies.remove(target)
                }
            }
        }
        else if (attackResult == AttackResult.DODGE) {
            Console.send("${attacker.type.capitalize()} attacked ${target.type} with ${attacker.refer("their")} ${weapon.name}, but ${target.type} dodged. " +
                    "${target.type.capitalize()} ${target.refer("is")} at ${target.health} health.")
        }
        else if (attackResult == AttackResult.BLOCK) {
            Console.send("${attacker.type.capitalize()} attacked ${target.type} with ${attacker.refer("their")} ${weapon.name}," +
                    " but ${target.type} blocked with ${target.refer("their")} ${target.shieldItem!!.name}.")
        }
    }

    fun surpriseAttack(attacker : AbstractCharacter, weapon: WeaponItem) {
        var damage : Int = weapon.use(attacker) as Int

        GameData.player.takeDamage(damage)

        if (attacker.customEntrance) {
            Console.send(attacker.enter(true, weapon, damage))
        }
        else {
            Console.send("A ${attacker.type} surprised you, attacking with their ${weapon.name} and dealing $damage damage.")
        }
    }

    fun block(blocker : AbstractCharacter, shieldItem: ShieldItem) {
        blocker.shieldItem = shieldItem
        Console.send("${blocker.type} raised ${blocker.refer("their")} ${shieldItem.name}.")
    }

    private fun isOver() : Boolean {
        return enemies.isEmpty()
    }

    fun getEnemy(name : String) : AbstractCharacter? {
        for (enemy in enemies) {
            if (enemy.name == name) {
                return enemy
            }
        }

        return null
    }

    enum class Stage {
        START,
        ONGOING,
        DEFEAT,
        VICTORY,
    }

    enum class AttackResult {
        HIT,
        BLOCK,
        DODGE
    }
}