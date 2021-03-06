package main.characters

import main.olditems.Items
import main.olditems.ShieldItem
import main.olditems.WeaponItem
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
        weapons.add(Items.SWORD)
        shields.add(ShieldItem("shield", 3))
    }

    override fun takeTurn() {

        var input : ArrayList<Any> = Console.listen(Console.ATTACK_COMMAND, Console.BLOCK_COMMAND) as ArrayList<Any>

        if (input[0] == "block") {
            GameData.encounter.block(this, getShield(input[1] as String)!!)
        }
        else if (input[0] == "attack") {
            GameData.encounter.attack(this, GameData.encounter.getEnemy(input[1] as String)!!, getWeapon(input[2] as String)!!)
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

        val input : String = Console.listen(Console.SKILL_COMMAND)[1] as String
        parseSkillInput(input)

        if (skillPoints > 0) {
            chooseSkill()
        }
    }

    private fun parseSkillInput(input : String) {

        if (input == "strength") {
            strength += 1
            skillPoints -= 1
            Console.send("You now have ${strength} strength points.")
            return
        }
        else if (input == "dexterity") {
            dexterity += 1
            skillPoints -= 1
            Console.send("You now have ${dexterity} dexterity points.")
            return
        }

        Console.send("You cannot become skilled in the ways of $input in this game.")
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

    fun reset() {
        health = maxHealth
        level = 0
        skillPoints = 0
    }
}