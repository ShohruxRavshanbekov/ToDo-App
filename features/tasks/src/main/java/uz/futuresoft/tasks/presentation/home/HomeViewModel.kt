package uz.futuresoft.tasks.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.futuresoft.tasks.domain.models.TodoItem
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository

class HomeViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TodoItem>>(emptyList())
    val tasks: StateFlow<List<TodoItem>>
        get() = _tasks.asStateFlow()


    fun getTasks() {
        _tasks.value = todoItemsRepository.getTodos()
    }

    fun markAsCompleted(id: String, task: TodoItem) {
        todoItemsRepository.saveTask(id = id, task = task)
        getTasks()
    }

    fun removeTask(id: String) {
        todoItemsRepository.removeTask(id = id)
        getTasks()
    }
}