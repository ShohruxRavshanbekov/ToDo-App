package uz.futuresoft.tasks.utils

import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.tasks.common.models.ToDoItemState
import java.util.Date

internal fun ToDoItemState.toTodoItem(): ToDoItem {
    return ToDoItem(
        id = this.id,
        text = this.text,
        importance = this.importance.value,
        createdAt = this.createdAt.time,
        done = this.isCompleted,
        deadline = this.deadline?.time,
        changedAt = this.modifiedAt?.time
    )
}

internal fun ToDoItem.toTodoItemState(): ToDoItemState {
    val importance = when (this.importance) {
        TodoItemImportance.LOW.value -> TodoItemImportance.LOW
        TodoItemImportance.HIGH.value -> TodoItemImportance.HIGH
        TodoItemImportance.NORMAL.value -> TodoItemImportance.NORMAL
        else -> TodoItemImportance.NORMAL
    }
    return ToDoItemState(
        id = this.id,
        text = this.text,
        importance = importance,
        createdAt = Date(this.createdAt),
        isCompleted = this.done,
        deadline = Date(this.deadline ?: 0L),
        modifiedAt = Date(this.changedAt ?: 0L)
    )
}