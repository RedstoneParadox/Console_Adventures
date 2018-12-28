package main.console


/**
 * Created by RedstoneParadox on 12/25/2018.
 */
abstract class AbstractArgument(val argumentType: ArgumentType, val name : String) {

    fun matchArgument(argument : Any) : Boolean {
        if (argumentType.kclass != argument::class) {
            return false
        }


        return getExpectedValues(argument)
    }

    abstract fun getExpectedValues(argument: Any) : Boolean
}