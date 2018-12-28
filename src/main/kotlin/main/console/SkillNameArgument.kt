package main.console

/**
 * Created by RedstoneParadox on 12/27/2018.
 */
class SkillNameArgument(argumentType: ArgumentType) : AbstractArgument(argumentType) {

    override fun getExpectedValues(argument: Any): Boolean {
        for (skill in arrayListOf<String>("strength","dexterity")) {
            if (skill == argument) {
                return true
            }
        }

        return false
    }
}