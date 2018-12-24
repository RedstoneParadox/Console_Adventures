package main.pool

import main.Items.Items

/**
 * Created by RedstoneParadox on 12/23/2018.
 */
object WeightedPools {

    fun initPools() {
        initEquipmentPools()
        initNamePools()
    }

    //Enemy inventory
    val basicEnemyWeapons : WeightedPool = WeightedPool()
    val enemyWeapons : WeightedPool = WeightedPool()

    private fun initEquipmentPools() {
        //Enemy starter inventory
        basicEnemyWeapons.addEntry(Items.PET_STICK, 19)
        basicEnemyWeapons.addEntry(Items.SWORD, 2)

        //Switch enemy inventory to this after the player reaches level 5
        enemyWeapons.addEntry(Items.SWORD, 3)
        enemyWeapons.addEntry(Items.SPEAR, 4)
        enemyWeapons.addEntry(Items.PET_STICK, 8)
    }

    //Name pools
    val genericEnemyNames : DecreasingWeightedPool = DecreasingWeightedPool()

    private fun initNamePools() {
        genericEnemyNames.addEntry("Bob")
        genericEnemyNames.addEntry("Billy")
        genericEnemyNames.addEntry("Dave")
        genericEnemyNames.addEntry("Joe")
        genericEnemyNames.addEntry("Steve")
        genericEnemyNames.addEntry("Alex")
        genericEnemyNames.addEntry("Gary")
        genericEnemyNames.addEntry("Jasper")
        genericEnemyNames.addEntry("Michael")
        genericEnemyNames.addEntry("Eric")
        genericEnemyNames.addEntry("Johny")
        genericEnemyNames.addEntry("Mason")
    }

    //Spawn pools


}