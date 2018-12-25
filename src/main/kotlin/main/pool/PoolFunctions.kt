package main.pool

/**
 * Created by RedstoneParadox on 12/24/2018.
 */
object PoolFunctions : IPoolFunction {

    val shrunkEntries : ArrayList<Entry> = ArrayList()

    override fun parseFunction(entry: Entry) {

        when (entry.func) {
            "shrink" -> shrinkPool(entry)
        }
    }

    fun shrinkPool(entry: Entry) {
        shrunkEntries.add(Entry(entry, entry.weight, ""))
        entry.weight = 0
    }

    fun restorePool() {

        for (entry in shrunkEntries) {
            (entry.item as Entry).weight = entry.weight
        }

        shrunkEntries.clear()
    }
}