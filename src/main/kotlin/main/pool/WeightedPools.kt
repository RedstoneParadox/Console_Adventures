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
    val genericEnemyNames : WeightedPool = WeightedPool()

    private fun initNamePools() {
        genericEnemyNames.addEntry("Bob", func = "shrink")
        genericEnemyNames.addEntry("Billy", func = "shrink")
        genericEnemyNames.addEntry("Dave", func = "shrink")
        genericEnemyNames.addEntry("Joe", func = "shrink")
        genericEnemyNames.addEntry("Steve", func = "shrink")
        genericEnemyNames.addEntry("Alex", func = "shrink")
        genericEnemyNames.addEntry("Gary", func = "shrink")
        genericEnemyNames.addEntry("Jasper", func = "shrink")
        genericEnemyNames.addEntry("Michael", func = "shrink")
        genericEnemyNames.addEntry("Eric", func = "shrink")
        genericEnemyNames.addEntry("Johny", func = "shrink")
        genericEnemyNames.addEntry("Mason", func = "shrink")
    }

    //Spawn pools


}