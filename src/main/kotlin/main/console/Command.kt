package main.console

/**
 * Created by RedstoneParadox on 12/27/2018.
 */
class Command(val name: String) {

    val arguments : ArrayList<AbstractArgument> = ArrayList()

    fun addArgument(abstractArgument: AbstractArgument) {
        arguments.add(abstractArgument)
    }
}
