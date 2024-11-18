package uz.futuresoft.data.models

data class ToDoItem(
    val id: String,
    val text: String,
    val importance: String,
    val deadline: Long? = null,
    val isCompleted: Boolean,
    val createdAt: Long,
    val modifiedAt: Long?,
)
