package main.console

import main.util.INameable

/**
 * Created by RedstoneParadox on 12/27/2018.
 */
class NameArgument(argumentType: ArgumentType, name: String, val list : () -> ArrayList<*>) : AbstractArgument(argumentType, name) {

    override fun getExpectedValues(argument: Any): Boolean {
        for (nameable in list.invoke()) {
            if ((nameable as INameable).name == argument) {
                return true
            }
        }

        return false
    }
}