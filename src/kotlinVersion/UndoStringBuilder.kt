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
                calls.add(operation.copy(string = str.toString()))
                str.deleteCharAt(operation.index).toString()
            }
            is Undo -> {
                str.replace(0, str.length, calls.peek().string)
                calls.pop().string
                str.toString()
            }
            is Create -> {
                calls.add(operation)
                str.append(operation.string)
                return operation.string
            }
            is Insert -> {
                calls.add(operation.copy(string = str.toString()))
                str.insert(operation.index, operation.string).toString()
            }
            is Duplicate -> {
                calls.add(operation.copy(string = str.toString()))
                str.insert(operation.index, str[operation.index]).toString()
            }
        }
    }

}

