package uz.futuresoft.data.models

data class ToDoItem(
    val id: String? = null,
    val text: String? = null,
    val importance: String? = null,
    val deadline: Long? = null,
    val isCompleted: Boolean? = null,
    val createdAt: Long? = null,
    val modifiedAt: Long? = null,
)
