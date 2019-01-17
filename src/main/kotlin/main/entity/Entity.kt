package main.entity

import main.console.Console
import main.entity.ai.AIGoal

/**
 * Created by RedstoneParadox on 1/16/2019.
 */
class Entity(val name : String, var health : Int) {

    //Stats
    private var strength = 0
    private var dexterity = 0
    private var endurance = 0
    private var charisma = 0

    //Goals
    private var goals : ArrayList<AIGoal> = ArrayList()

    //Equipment

    fun takeTurn() {
        queryGoals(GoalType.TURN_START)
    }

    fun addGoal(goalSupplier : (entity : Entity) -> AIGoal) {
        val goal = goalSupplier.invoke(this)
        goals.add(goal)
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
    fun increaseStat(stat : String, amount : Int = 1) {

        when (stat) {
            "strength" -> strength += amount
            "dexterity" -> dexterity += amount
            "endurance" -> endurance += amount
            "charisma" -> charisma += amount
            else -> Console.send("This game does not allow you to become skilled in the ways of $stat.")
        }
    }

    enum class GoalType {
        TURN_START,
        TURN_END
    }
}