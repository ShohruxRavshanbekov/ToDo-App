package uz.futuresoft.tasks.domain.models

import uz.futuresoft.tasks.common.models.Importance
import java.util.Date

data class ToDoItem(
    val id: String,
    val text: String,
    val importance: Importance,
    val deadline: Date? = null,
    val isCompleted: Boolean,
    val createdAt: Date,
    val modifiedAt: Date? = null,
)
