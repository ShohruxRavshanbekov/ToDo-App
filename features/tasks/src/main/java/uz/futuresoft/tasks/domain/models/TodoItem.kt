package uz.futuresoft.tasks.domain.models

import uz.futuresoft.tasks.common.models.TodoItemImportance
import java.util.Date

data class TodoItem(
    val id: String,
    val text: String,
    val createdAt: Date,
    val importance: TodoItemImportance,
    val isCompleted: Boolean,
    val deadline: Date? = null,
    val modifiedAt: Date? = null,
)
