package uz.futuresoft.tasks.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.futuresoft.tasks.common.models.ToDoItemState
import uz.futuresoft.data.repositories.TodoItemsRepository2

class HomeViewModel(
    private val todoItemsRepository: TodoItemsRepository2,
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<ToDoItemState>>(emptyList())
    val tasks: StateFlow<List<ToDoItemState>>
        get() = _tasks.asStateFlow()

    fun getTasks() {
        _tasks.value = todoItemsRepository.getTodos()
    }

    fun markAsCompleted(id: String, task: ToDoItemState) {
        todoItemsRepository.saveTask(id = id, task = task)
        getTasks()
    }

    fun removeTask(id: String) {
        todoItemsRepository.removeTask(id = id)
        getTasks()
    }
}