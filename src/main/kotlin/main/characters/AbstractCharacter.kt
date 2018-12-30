package main.characters

import main.Items.AbstractItem
import main.Items.ShieldItem
import main.Items.WeaponItem
import main.encounter.Encounter
import main.util.INameable
import kotlin.random.Random

/**
 * Created by RedstoneParadox on 12/22/2018.
 */
abstract class AbstractCharacter(val maxHealth : Int, var type : String) : INameable {

    val inventory : ArrayList<AbstractItem> = ArrayList()
    val weapons : ArrayList<WeaponItem> = ArrayList()
    val shields : ArrayList<ShieldItem> = ArrayList()

    var health : Int = maxHealth
    var strength : Int = 0
    var dexterity : Int = 0

    var shieldItem : ShieldItem? = null

    open val customEntrance : Boolean = false

    open val names : ArrayList<String> = ArrayList()
    override var name: String = "???"

    abstract fun takeTurn()

    fun takeDamage(amount : Int) : Encounter.AttackResult {
        val dodgeRoll : Int = Random.nextInt(1, 100)

        if (dodgeRoll <= dexterity) {
            return Encounter.AttackResult.DODGE
        }

        if (shieldItem != null) {
            val blockRoll : Int = shieldItem!!.use(this) as Int

            if (blockRoll - strength < shieldItem!!.defense) {
                return Encounter.AttackResult.BLOCK
            }
        }

        health -= amount

        if (health <= 0) {
            health = 0
        }

        return Encounter.AttackResult.HIT
    }

    abstract fun enter(surprise: Boolean, weapon: WeaponItem?, damage: Int): String

    fun isDead() : Boolean {
        return health == 0
    }

    open fun refer(pronoun : String): String {
        return pronoun
    }

    fun getWeapon(name : String) : WeaponItem? {

        for (weapon in weapons) {
            if (weapon.name == name) {
                return weapon
            }
        }

        return null
    }

    fun getShield(name : String) : ShieldItem?{

        for (shield in shields) {
            if (shield.name == name) {
                return shield
            }
        }

        return null
    }
}