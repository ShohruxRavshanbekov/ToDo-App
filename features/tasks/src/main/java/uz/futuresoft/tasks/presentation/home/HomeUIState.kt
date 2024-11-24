package uz.futuresoft.tasks.presentation.home

import uz.futuresoft.data.models.ToDoItem

data class HomeUIState(
    val loading: Boolean = false,
    val tasks: List<ToDoItem> = emptyList(),
    val error: Throwable? = null,
)