package main.olditems

import main.characters.AbstractCharacter
import kotlin.random.Random

/**
 * Created by RedstoneParadox on 12/23/2018.
 */
class ShieldItem(name: String, val defense : Int) : AbstractItem(name) {

    override fun use(user: AbstractCharacter): Any {
        return Random.nextInt(0, defense + 3)
    }
}