package uz.futuresoft.tasks.utils

import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.tasks.common.models.ToDoItemState

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