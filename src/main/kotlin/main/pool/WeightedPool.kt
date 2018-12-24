package main.pool

import kotlin.random.Random

/**
 * Created by RedstoneParadox on 12/23/2018.
 */
open class WeightedPool {

    protected var poolEntries: ArrayList<WeightedEntry> = ArrayList()

    protected var emptyWeight: Int = 0

    fun addEntry(any: Any, weight : Int = 1) {
        poolEntries.add(WeightedEntry(any, weight))
    }

    fun addNestedPool(weight: Int = 1): WeightedPool {
        val pool : WeightedPool = WeightedPool()
        poolEntries.add(WeightedEntry(pool, weight))
        return pool
    }

    open fun loot(rolls : Int) : Any {
        return roll(this).item
    }

    fun roll(pool: WeightedPool) : WeightedEntry {
        var roll : Int = Random.nextInt(0, pool.totalWeight())

        for (entry in pool.poolEntries) {
            if (roll <= entry.weight) {
                if (entry.item is WeightedPool) {
                     return roll(entry.item)
                }
                else {
                    return entry
                }
            }
            else {
                roll -= entry.weight
            }
        }

        return WeightedEntry(Any(), 0)
    }

    private fun totalWeight() : Int {
        var weight : Int = 0

        for (entry in poolEntries) {
           weight += entry.weight
        }

        return weight + this.emptyWeight
    }

    class WeightedEntry(val item : Any, val weight: Int)
}