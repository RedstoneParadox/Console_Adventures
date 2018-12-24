package main.pool

/**
 * Created by RedstoneParadox on 12/23/2018.
 */
class DecreasingWeightedPool : WeightedPool() {

    var originalPool: ArrayList<WeightedEntry> = ArrayList()

    override fun loot(rolls : Int): Any {
        val results: ArrayList<Any> = ArrayList()

        originalPool = poolEntries

        if (rolls == 1) {
            var entry = super.roll(this)
            results.add(entry.item)
            poolEntries = originalPool
            return results
        }

        for (i in 1..rolls) {
            var entry = super.roll(this)
            results.add(entry.item)
            poolEntries.remove(entry)
        }

        poolEntries = originalPool
        return results
    }
}