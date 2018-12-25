package main.pool

import kotlin.random.Random

/**
 * Created by RedstoneParadox on 12/23/2018.
 */
open class WeightedPool {

    protected var poolEntries: ArrayList<Entry> = ArrayList()

    protected var emptyWeight: Int = 0

    fun addEntry(any: Any, weight : Int = 1, func : String = "") {
        poolEntries.add(Entry(any, weight, func))
    }

    fun addNestedPool(weight: Int = 1, func: String = ""): WeightedPool {
        val pool : WeightedPool = WeightedPool()
        poolEntries.add(Entry(pool, weight, func))
        return pool
    }

    open fun loot(rolls : Int) : ArrayList<Any> {
        val results: ArrayList<Any> = ArrayList()

        for (i in 0..rolls) {
            results.add(roll(this).item)
        }

        return results
    }

    fun roll(pool: WeightedPool) : Entry {
        var roll : Int = Random.nextInt(0, pool.totalWeight())

        for (entry in pool.poolEntries) {
            if (roll <= entry.weight) {
                if (entry.item is WeightedPool) {
                     return roll(entry.item as WeightedPool)
                }
                else {
                    if (entry.func != "") {
                        PoolFunctions.parseFunction(entry)
                        return entry
                    }
                    return entry
                }
            }
            else {
                roll -= entry.weight
            }
        }

        return Entry(Any(), 0, "")
    }

    private fun totalWeight() : Int {
        var weight : Int = 0

        for (entry in poolEntries) {
           weight += entry.weight
        }

        return weight + this.emptyWeight
    }

}