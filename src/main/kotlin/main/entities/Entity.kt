package main.entities

import main.console.Console
import main.entities.ai.AIGoal

/**
 * Created by RedstoneParadox on 1/16/2019.
 */
class Entity {

    //Stats
    private var strength = 0
    private var dexterity = 0
    private var endurance = 0
    private var charisma = 0

    //Goals
    private var goals : ArrayList<AIGoal> = ArrayList()

    //Equipment

    //Other
    var name = ""
    var health = 1

    fun takeTurn() {
        queryGoals(GoalType.TURN_START)
    }

    fun addGoal(goal : AIGoal) {
        goal.entity = this
        goals + goal
    }

    private fun queryGoals(goalType: GoalType) {

        for (goal in goals) {
            when (goal) {
                GoalType.TURN_START -> goal.onTurnStart()
                GoalType.TURN_END -> goal.onTurnEnd()
            }
        }
    }

    //Increasing stats
    fun increaseStat(stat : String, amount : Int) {

        when (stat) {
            "strength" -> strength += amount
            "dexterity" -> dexterity += amount
            "endurance" -> endurance += amount
            "charisma" -> charisma += amount
            else -> Console.send("Error: That stat does not exist.")
        }
    }

    enum class GoalType {
        TURN_START,
        TURN_END
    }
}