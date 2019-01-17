package main.pool

import main.olditems.Items

/**
 * Created by RedstoneParadox on 12/23/2018.
 */
object WeightedPools {

    fun initPools() {
        initEquipmentPools()
        initNamePools()
    }

    //Enemy inventory
    val BASIC_ENEMY_WEAPONS : WeightedPool = WeightedPool()
    val ENEMY_WEAPONS : WeightedPool = WeightedPool()

    private fun initEquipmentPools() {
        //Enemy starter inventory
        BASIC_ENEMY_WEAPONS.addEntry(Items.PET_STICK, 19)
        BASIC_ENEMY_WEAPONS.addEntry(Items.SWORD, 2)

        //Switch enemy inventory to this after the player reaches level 5
        ENEMY_WEAPONS.addEntry(Items.SWORD, 3)
        ENEMY_WEAPONS.addEntry(Items.SPEAR, 4)
        ENEMY_WEAPONS.addEntry(Items.PET_STICK, 8)
    }

    //Name pools
    val GENERIC_ENEMY_NAMES : WeightedPool = WeightedPool()

    private fun initNamePools() {
        GENERIC_ENEMY_NAMES.addEntry("Bob", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Billy", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Dave", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Joe", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Steve", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Alex", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Garry", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Jasper", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Michael", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Eric", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Johnny", func = "shrink")
        GENERIC_ENEMY_NAMES.addEntry("Mason", func = "shrink")
    }

    //Spawn pools


}