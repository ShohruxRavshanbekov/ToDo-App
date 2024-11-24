package uz.futuresoft.tasks.presentation.task_events

import uz.futuresoft.data.models.ToDoItem

data class TaskDetailsUIState(
    val loading: Boolean = false,
    val error: Throwable? = null,
    val task: ToDoItem = ToDoItem(
        id = "",
        text = "",
        importance = "",
        isCompleted = false,
        createdAt = 0L,
        modifiedAt = 0L,
    ),
)