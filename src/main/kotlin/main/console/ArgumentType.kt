package main.console

import kotlin.reflect.KClass

/**
 * Created by RedstoneParadox on 12/27/2018.
 */
enum class ArgumentType(val kclass: KClass<*>) {
    STRING(String::class),
    INTEGER(Int::class)
}