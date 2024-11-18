package uz.futuresoft.tasks.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository

class HomeViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _gettingTasksInProgress = MutableStateFlow(false)
    val gettingTasksInProgress: StateFlow<Boolean>
        get() = _gettingTasksInProgress.asStateFlow()

    private val _markingTaskAsCompletedInProgress = MutableStateFlow(false)
    val markingTaskAsCompletedInProgress: StateFlow<Boolean>
        get() = _markingTaskAsCompletedInProgress.asStateFlow()

    private val _deletingTaskInProgress = MutableStateFlow(false)
    val deletingTaskInProgress: StateFlow<Boolean>
        get() = _deletingTaskInProgress.asStateFlow()

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?>
        get() = _error.asStateFlow()

    private val _tasks = MutableStateFlow<List<ToDoItem>>(emptyList())
    val tasks: StateFlow<List<ToDoItem>>
        get() = _tasks.asStateFlow()

    init {
        viewModelScope.launch {
            todoItemsRepository.tasks.collect {
                _tasks.value = it
                _gettingTasksInProgress.value = false
                _markingTaskAsCompletedInProgress.value = false
                _deletingTaskInProgress.value = false
            }
        }

        viewModelScope.launch {
            todoItemsRepository.error.collect {
                _error.value = it
                _gettingTasksInProgress.value = false
                _markingTaskAsCompletedInProgress.value = false
                _deletingTaskInProgress.value = false
            }
        }
    }

    suspend fun getTasks() {
        _gettingTasksInProgress.value = true
        todoItemsRepository.getTodos()
    }

    suspend fun syncWithServer(revision: Int, tasks: List<ToDoItem>) {
        _gettingTasksInProgress.value = true
        todoItemsRepository.syncWithServer(revision = revision, tasks = tasks)
    }

    suspend fun markAsCompleted(revision: Int, taskId: String, task: ToDoItem) {
        _markingTaskAsCompletedInProgress.value = true
        todoItemsRepository.updateTask(revision = revision, taskId = taskId, task = task)
    }

    suspend fun removeTask(revision: Int, taskId: String) {
        _deletingTaskInProgress.value = true
        todoItemsRepository.deleteTask(revision = revision, taskId = taskId)
    }
}