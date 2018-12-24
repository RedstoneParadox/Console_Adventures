package main.characters

import main.Items.WeaponItem
import main.encounter.Encounter
import main.pool.WeightedPools
import kotlin.random.Random

/**
 * Created by RedstoneParadox on 12/22/2018.
 */
class GenericEnemy(type: String, maxHealth: Int, val exp: Int, val encounter: Encounter) : AbstractCharacter(maxHealth, type) {


    override var name : String = ""

    var surpriseEntrances : ArrayList<String> = arrayListOf(
        "the rafters",
        "the bushes",
        "a hole in the ground"
    )

    override val customEntrance: Boolean = true

    init {
        if (encounter.player.level >= 5) {
            inventory.add(WeightedPools.enemyWeapons.loot(1) as WeaponItem)
        }
        else {
            inventory.add(WeightedPools.basicEnemyWeapons.loot(1) as WeaponItem)
        }
    }

    override fun takeTurn(encounter : Encounter) {

        encounter.attack(this, encounter.player, inventory[0] as WeaponItem)

    }

    fun surpriseAttack(encounter: Encounter) {

        encounter.surpriseAttack(this, inventory[0] as WeaponItem)
    }

    fun dropEXP() : Int {
        return Random.nextInt(exp - 2, exp + 2)
    }

    override fun enter(surprise: Boolean, weapon: WeaponItem?, damage: Int) : String {
        type = name

        if (surprise) {
            val entranceRoll : Int = Random.nextInt(0, surpriseEntrances.size)

            return "$name jumps out from ${surpriseEntrances[entranceRoll]} and smacks you with their " +
                    "${weapon!!.name}, dealing ${damage} damage."

        }

        return "$name approaches you with their ${inventory[0].name}."
    }

}