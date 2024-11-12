package uz.futuresoft.tasks.common.models

import uz.futuresoft.tasks.utils.TodoItemImportance
import java.util.Date

data class ToDoItemState(
    val id: String,
    val text: String,
    val createdAt: Date,
    val importance: TodoItemImportance,
    val isCompleted: Boolean,
    val deadline: Date? = null,
    val modifiedAt: Date? = null,
)
