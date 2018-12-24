package main.Items

import main.characters.AbstractCharacter

/**
 * Created by RedstoneParadox on 12/22/2018.
 */
abstract class AbstractItem(val name : String) {

    abstract fun use(user: AbstractCharacter): Any
}