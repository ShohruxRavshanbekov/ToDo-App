package uz.futuresoft.data.models

data class ToDoItem(
    val id: String,
    val text: String,
    val importance: String,
    val deadline: Long? = null,
    val done: Boolean,
    val createdAt: Long,
    val changedAt: Long? = null,
)
