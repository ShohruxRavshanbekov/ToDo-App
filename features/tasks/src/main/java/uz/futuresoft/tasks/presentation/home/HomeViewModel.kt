package uz.futuresoft.tasks.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.futuresoft.tasks.domain.models.TodoItem
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository

class HomeViewModel(
    private val toDoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TodoItem>>(emptyList())
    val tasks: StateFlow<List<TodoItem>>
        get() = _tasks.asStateFlow()

    init {
        getTasks()
    }

    private fun getTasks() {
        _tasks.value = toDoItemsRepository.getTodos()
    }

    fun removeTask(task: TodoItem) {
        toDoItemsRepository.removeTask(task = task)
    }

    fun markAsCompleted(task: TodoItem) {
        toDoItemsRepository.saveTask(task = task)
    }
}