package main.console

/**
 * Created by RedstoneParadox on 12/25/2018.
 */
object Console {

    var input : String? = null

    var commands : ArrayList<Command> = ArrayList()
    //Commands
    lateinit var HELP_COMMAND : Command
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
        START_COMMAND = registerCommand("start", "starts the game.")

        HELP_COMMAND = registerCommand("help", "Lists all commands.")

        ATTACK_COMMAND = registerCommand("attack", "Attacks the target enemy with the chosen weapon.")
        ATTACK_COMMAND.addArgument(EnemyNameArgument(ArgumentType.STRING, "enemy name"))

        BLOCK_COMMAND = registerCommand("block", "Blocks with the chosen shield.")

        START_COMMAND = registerCommand("start", "Starts the game.")

        CONTINUE_COMMAND = registerCommand("continue", "Continues.")

        RETRY_COMMAND = registerCommand("retry", "Hi.")

        SKILL_COMMAND = registerCommand("skill", "Used to upgrade one of your skills. You get skill points when you level up.")
        SKILL_COMMAND.addArgument(SkillNameArgument(ArgumentType.STRING, "skill"))
    }

    fun registerCommand(name : String, description: String) : Command {
        var command = Command(name, description)
        commands.add(command)
        return command
    }

    fun printCommands() {
        for (command in commands) {
            var info: String = "[${command.name}"

            if (!command.arguments.isEmpty()) {
                for (argument in command.arguments) {
                    info += " {${argument.name}}"
                }
            }

            info += "] ${command.description}"
            send(info)
        }
    }
}