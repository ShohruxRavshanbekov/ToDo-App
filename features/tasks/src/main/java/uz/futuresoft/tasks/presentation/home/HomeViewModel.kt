package uz.futuresoft.tasks.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.futuresoft.core.receivers.NetworkChangeReceiver
import uz.futuresoft.core.utils.AppSharedPreferences
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository

class HomeViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val lastRevision = AppSharedPreferences.getInt(key = AppSharedPreferences.KEY_REVISION)

    private val _isTasksLoading = MutableStateFlow(false)
    val isTasksLoading: StateFlow<Boolean>
        get() = _isTasksLoading.asStateFlow()

    private val _isTaskModifyInProgress = MutableStateFlow(false)
    val isTaskModifyInProgress: StateFlow<Boolean>
        get() = _isTaskModifyInProgress.asStateFlow()

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
                _isTasksLoading.value = false
                _isTaskModifyInProgress.value = false
            }
        }

        viewModelScope.launch {
            todoItemsRepository.error.collect {
                Log.e(null, "ERROR:", it)
                _error.value = it
                _isTasksLoading.value = false
                _isTaskModifyInProgress.value = false
            }
        }
    }

    suspend fun getTasks() {
        _isTasksLoading.value = true
        todoItemsRepository.getTodos()
    }

    suspend fun syncWithServer(tasks: List<ToDoItem>) {
        _isTasksLoading.value = true
        todoItemsRepository.syncWithServer(revision = lastRevision, tasks = tasks)
    }

    fun markAsCompleted(taskId: String, task: ToDoItem) {
        _isTaskModifyInProgress.value = true
        viewModelScope.launch {
            todoItemsRepository.updateTask(revision = lastRevision, taskId = taskId, task = task)
        }
    }

    fun removeTask(taskId: String) {
        _isTaskModifyInProgress.value = true
        viewModelScope.launch {
            todoItemsRepository.deleteTask(revision = lastRevision, taskId = taskId)
        }
    }
}