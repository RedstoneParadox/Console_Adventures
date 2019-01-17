package main.olditems

import main.characters.AbstractCharacter
import kotlin.random.Random

/**
 * Created by RedstoneParadox on 12/22/2018.
 */
class WeaponItem(name: String, val minDamage : Int, val maxDamage : Int = minDamage) : AbstractItem(name) {

    override fun use(user: AbstractCharacter): Any {

        if (minDamage == maxDamage) {
            return maxDamage
        }
        return Random.nextInt(minDamage, maxDamage + 1)
    }


}