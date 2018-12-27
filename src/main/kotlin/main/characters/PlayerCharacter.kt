package main.characters

import main.Items.Items
import main.Items.ShieldItem
import main.Items.WeaponItem
import main.console.Console
import main.util.GameData

/**
 * Created by RedstoneParadox on 12/22/2018.
 */
class PlayerCharacter(maxHealth: Int, name: String) : AbstractCharacter(maxHealth, name) {

    var exp : Int = 0
    var level : Int = 0
    var skillPoints : Int = 0

    init {
        inventory.add(Items.SWORD)
        inventory.add(ShieldItem("shield", 3))
    }

    override fun takeTurn() {

        var input : ArrayList<Any> = Console.listen("attack", "block") as ArrayList<Any>

        if (input[0] == "block") {
            GameData.encounter.block(this, inventory[1] as ShieldItem)
        }
        else if (input[0] == "attack") {
            GameData.encounter.attack(this, GameData.encounter.getEnemy(input[1] as String)!!, inventory[0] as WeaponItem)
        }
    }

    fun gainEXP(exp : Int) {

        this.exp += exp
        val xpGain : String = "You gained $exp experience."

        if (this.exp >= levelReq()) {
            this.exp -= levelReq()
            this.level += 1
            this.skillPoints += 1
            Console.send("$xpGain You are now level $level. Only ${levelReq() - this.exp} more experience to go until level ${level + 1}.")
        }
        else {
            Console.send("$xpGain Only ${levelReq() - this.exp} more experience to go until level ${level + 1}.")
        }
    }

    fun chooseSkill() {
        var chosen : Boolean = false

        Console.send("You have $skillPoints unspent skill points.")

        if (!chosen) {
            Console.send("You may spend them to upgrade your [strength] or [dexterity] skills.")
            chosen = true
        }

        while (true) {
            val input : String? = readLine()

            if (input is String) {
                if (parseSkillInput(input)) {
                    break
                }
            }
        }

        if (skillPoints > 0) {
            chooseSkill()
        }
    }

    private fun parseSkillInput(input : String) : Boolean {

        if (input == "strength") {
            strength += 1
            skillPoints -= 1
            Console.send("You now have ${strength} strength points.")
            return true
        }
        else if (input == "dexterity") {
            dexterity += 1
            skillPoints -= 1
            Console.send("You now have ${dexterity} dexterity points.")
            return true
        }

        return false
    }

    private fun levelReq() : Int {
        return 10 + (4 * level)
    }

    fun hasPoints() : Boolean {
        return skillPoints > 0
    }

    override fun refer(pronoun: String): String {
        return when (pronoun) {
            "them" -> "you"
            "is" -> "are"
            "their" -> "your"
            else -> "$pronoun.player_form"
        }
    }

    override fun enter(surprise: Boolean, weapon: WeaponItem?, damage: Int): String {
        return "The player somehow enters...you're not supposed to be able to do this."
    }
}