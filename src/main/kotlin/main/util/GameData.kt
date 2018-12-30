package main.util

import main.characters.PlayerCharacter
import main.encounter.Encounter

/**
 * Created by RedstoneParadox on 12/27/2018.
 */
object GameData {

    lateinit var encounter: Encounter
    val player: PlayerCharacter = PlayerCharacter(10, "you")

}