package kotlinVersion

sealed class Operation {
    abstract val string: String

    data class Duplicate(var index: Int, override val string: String = "") : Operation()
    data class Delete(var index: Int, override val string: String = "") : Operation()
    data class Insert(var index: Int, override val string: String) : Operation()
    data class Undo(override val string: String = "") : Operation()
    data class Create(override val string: String) : Operation()
}