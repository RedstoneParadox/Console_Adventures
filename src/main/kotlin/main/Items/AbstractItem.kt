package main.Items

import main.characters.AbstractCharacter
import main.util.INameable

/**
 * Created by RedstoneParadox on 12/22/2018.
 */
abstract class AbstractItem(override var name : String) : INameable {

    abstract fun use(user: AbstractCharacter): Any
}