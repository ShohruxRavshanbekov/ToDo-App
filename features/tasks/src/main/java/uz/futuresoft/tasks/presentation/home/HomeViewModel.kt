package uz.futuresoft.tasks.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import java.net.SocketTimeoutException

class HomeViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading.asStateFlow()

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?>
        get() = _error.asStateFlow()

    private val _tasks = MutableStateFlow<List<ToDoItem>>(emptyList())
    val tasks: StateFlow<List<ToDoItem>>
        get() = _tasks.asStateFlow()

    init {
        _loading.value = true
        viewModelScope.launch {
            try {
                _loading.value = false
                todoItemsRepository.tasksFlow.collect { _tasks.value = it }
            } catch (e: SocketTimeoutException) {
                _loading.value = false
                _error.value = Throwable(message = "SocketTimeoutException")
            } catch (e: Throwable) {
                _loading.value = false
                _error.value = e
            }
        }
    }

    suspend fun getTasks() {
        todoItemsRepository.getTodos()
    }

    fun markAsCompleted(taskId: String, task: ToDoItem) {
        todoItemsRepository.updateTask(taskId = taskId, task = task)
    }

    fun removeTask(taskId: String) {
        todoItemsRepository.deleteTask(taskId = taskId)
    }
}