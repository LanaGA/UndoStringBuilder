package kotlinVersion

fun main() {
    val undoStringBuilder = UndoStringBuilder()
    undoStringBuilder.apply {
        println(reduce(Operation.Create("Hello")))
        println(reduce(Operation.Delete(1)))
        println(reduce(Operation.Undo()))
        println(reduce(Operation.Insert(0, "e")))
        println(reduce(Operation.Delete(0)))
        println(reduce(Operation.Undo()))
        println(reduce(Operation.Undo()))
        println(reduce(Operation.Duplicate(0)))
        println(reduce(Operation.Undo()))


    }
}