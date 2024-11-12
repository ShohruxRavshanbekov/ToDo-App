package uz.futuresoft.tasks.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.tasks.common.models.ToDoItemState
import uz.futuresoft.tasks.utils.toTodoItem

class HomeViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<ToDoItemState>>(emptyList())
    val tasks: StateFlow<List<ToDoItemState>>
        get() = _tasks.asStateFlow()

    suspend fun getTasks() {
        todoItemsRepository.getTodos()
    }

    fun markAsCompleted(taskId: String, task: ToDoItemState) {
        todoItemsRepository.updateTask(taskId = taskId, task = task.toTodoItem())
    }

    fun removeTask(taskId: String) {
        todoItemsRepository.deleteTask(taskId = taskId)
    }
}