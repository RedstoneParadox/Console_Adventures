package main.entities.ai

import main.entities.Entity

/**
 * Created by RedstoneParadox on 1/16/2019.
 */
interface AIGoal {

    var entity : Entity

    fun onTurnStart() {

    }

    fun onTurnEnd() {

    }
}