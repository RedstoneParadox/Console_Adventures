package main.console

/**
 * Created by RedstoneParadox on 12/25/2018.
 */
object Console {

    var input : String? = null

    var commands : ArrayList<Command> = ArrayList()
    //Commands
    lateinit var ATTACK_COMMAND : Command
    lateinit var BLOCK_COMMAND : Command
    lateinit var START_COMMAND : Command
    lateinit var CONTINUE_COMMAND : Command
    lateinit var RETRY_COMMAND : Command
    lateinit var SKILL_COMMAND : Command

    fun listen(vararg expectedCommands: Command): MutableList<Any> {
        while (true) {
            while (input !is String) {
                input = readLine()
            }

            val parsedInput = parseInput(*expectedCommands)

            input = null

            if (parsedInput != null) {
                return parsedInput
            }
            send("Invalid command.")
        }

    }

    private fun parseInput(vararg expectedCommands: Command): MutableList<Any>? {
        val segmentedInput : List<String> = (input as String).split(" ")
        val parsedInput: ArrayList<Any> = ArrayList()

        for (segment in segmentedInput) {
            try {
                parsedInput.add(segment.toInt())
            }
            catch (e : NumberFormatException) {
                parsedInput.add(segment)
            }
        }

        for (command in commands) {
            if (parsedInput[0] == command.name && isExpectedCommand(command.name, *expectedCommands)) {
                if ((parsedInput.size - 1) != command.arguments.size) {
                    return null
                }

                for (i in 1 until(parsedInput.size)) {
                    for (argument in command.arguments) {
                        if (!argument.matchArgument(parsedInput[i])) {
                            return null
                        }
                    }
                }
            }
        }

        return parsedInput
    }

    private fun isExpectedCommand(commandName : String, vararg expectedCommands: Command) : Boolean {
        for (expectedCommand in expectedCommands) {
            if (commandName == expectedCommand.name) {
                return true
            }
        }

        return false
    }



    fun send(output : String) {
        System.out.println("> " + output)
    }

    fun initCommands() {
        ATTACK_COMMAND = registerCommand("attack")
        ATTACK_COMMAND.addArgument(EnemyNameArgument(ArgumentType.STRING))

        BLOCK_COMMAND = registerCommand("block")

        START_COMMAND = registerCommand("start")

        CONTINUE_COMMAND = registerCommand("continue")

        RETRY_COMMAND = registerCommand("retry")

        SKILL_COMMAND = registerCommand("skill")
        SKILL_COMMAND.addArgument(SkillNameArgument(ArgumentType.STRING))
    }

    fun registerCommand(name : String) : Command {
        var command = Command(name)
        commands.add(command)
        return command
    }
}