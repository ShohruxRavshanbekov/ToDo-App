package uz.futuresoft.data

import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.network.models.TodoDTO

internal fun TodoDTO.toToDoItem(): ToDoItem {
    return ToDoItem(
        id = this.id.toString(),
        text = this.text.toString(),
        createdAt = this.createdAt ?: 0L,
        importance = this.importance.toString(),
        isCompleted = this.done ?: false,
        deadline = this.deadline,
        modifiedAt = this.changedAt,
    )
}

internal fun ToDoItem.toTodoDTO(): TodoDTO {
    return TodoDTO(
        id = this.id,
        text = this.text,
        createdAt = this.createdAt,
        importance = this.importance,
        done = this.isCompleted,
        deadline = this.deadline,
        changedAt = this.modifiedAt,
    )
}