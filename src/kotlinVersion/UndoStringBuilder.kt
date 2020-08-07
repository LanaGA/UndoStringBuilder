package kotlinVersion

import kotlinVersion.Operation.*
import java.lang.StringBuilder
import java.util.*

class UndoStringBuilder {

    private val str = StringBuilder()
    private val calls = Stack<Operation>()

    fun reduce(operation: Operation): String {
        return when (operation) {
            is Delete -> {
                try {
                    calls.add(operation.copy(string = str.toString()))
                    str.deleteCharAt(operation.index).toString()
                } catch (e: Exception) {
                    return "There are no character to delete!"
                }
            }
            is Undo -> {
                try {
                    str.replace(0, str.length, calls.peek().string)
                    calls.pop().string
                    str.toString()
                } catch (e: EmptyStackException) {
                    return "There are no operations to undo!"
                }

            }
            is Create -> {
                calls.add(operation.copy(string = str.toString()))
                str.append(operation.string)
                return operation.string
            }
            is Insert -> {
                calls.add(operation.copy(string = str.toString()))
                str.insert(operation.index, operation.string).toString()
            }
            is Duplicate -> {
                try {

                    calls.add(operation.copy(string = str.toString()))
                    str.insert(operation.index, str[operation.index]).toString()
                } catch (e: Exception) {
                    return "There are no character to duplicate!"
                }
            }
        }
    }

}

