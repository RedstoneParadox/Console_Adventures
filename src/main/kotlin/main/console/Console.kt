package main.console

/**
 * Created by RedstoneParadox on 12/25/2018.
 */
object Console {

    var input : String? = null

    var commands : ArrayList<Command> = ArrayList()
    //Commands
    lateinit var attackCommand : Command
    lateinit var blockCommand : Command

    fun listen(vararg expectedCommands: String): MutableList<Any> {
        while (true) {
            while (input !is String) {
                input = readLine()
            }

            val parsedInput = parseInput(*expectedCommands)

            if (parsedInput != null) {
                return parsedInput
            }
            send("Invalid command.")
        }

    }

    fun parseInput(vararg expectedCommands: String): MutableList<Any>? {
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
                val arguments : ArrayList<Any> = parsedInput
                arguments.removeAt(0)

                for (argument in command.arguments) {
                    if (!argument.matchArgument(arguments[0])) {
                        return null
                    }

                    arguments.removeAt(0)
                }
            }
        }

        return parsedInput
    }

    private fun isExpectedCommand(commandName : String, vararg expectedCommands: String) : Boolean {
        for (expectedCommand in expectedCommands) {
            if (commandName == expectedCommand) {
                return true
            }
        }

        return false
    }

    fun send(output : String) {
        System.out.println("> " + output)
    }

    fun initCommands() {
        attackCommand = registerCommand("attack")
        attackCommand.addArgument(EnemyNameArgument(ArgumentType.STRING))

        blockCommand = registerCommand("block")
    }

    fun registerCommand(name : String) : Command {
        var command = Command(name)
        commands.add(command)
        return command
    }
}