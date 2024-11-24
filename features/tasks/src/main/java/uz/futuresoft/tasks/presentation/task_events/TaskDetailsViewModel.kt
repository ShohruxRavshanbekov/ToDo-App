package uz.futuresoft.tasks.presentation.task_events

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.futuresoft.core.utils.AppSharedPreferences
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository

class TaskDetailsViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val lastRevision = AppSharedPreferences.getInt(key = AppSharedPreferences.KEY_REVISION)

    private val _isTaskLoading = MutableStateFlow(false)

    val isTaskLoading: StateFlow<Boolean>
        get() = _isTaskLoading.asStateFlow()

    private val _isTaskCreatingInProgress = MutableStateFlow(false)
    val isTaskCreatingInProgress: StateFlow<Boolean>
        get() = _isTaskCreatingInProgress.asStateFlow()

    private val _isTaskModifyInProgress = MutableStateFlow(false)
    val isTaskModifyInProgress: StateFlow<Boolean>
        get() = _isTaskModifyInProgress.asStateFlow()

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?>
        get() = _error.asStateFlow()

    private val _task = MutableStateFlow(
        ToDoItem(
            id = "",
            text = "",
            importance = "",
            isCompleted = false,
            createdAt = 0L,
            modifiedAt = 0L,
        )
    )
    val task: StateFlow<ToDoItem>
        get() = _task.asStateFlow()

    init {
        viewModelScope.launch {
            todoItemsRepository.taskModificationResponse.collect {
                if (it) {
                    _isTaskCreatingInProgress.value = false
                    _isTaskModifyInProgress.value = false
                }
            }
        }

        viewModelScope.launch {
            todoItemsRepository.error.collect {
                Log.e(null, "ERROR:", it)
                _error.value = it
                _isTaskLoading.value = false
                _isTaskCreatingInProgress.value = false
                _isTaskModifyInProgress.value = false
            }
        }
    }

    fun getTaskById(id: String) {
        _isTaskLoading.value = true
        viewModelScope.launch {
            try {
                _task.value = todoItemsRepository.getTaskById(taskId = id)
            } catch (e: Exception) {
                Log.e(null, "ERROR:", e)
                _error.value = e
            } finally {
                _isTaskLoading.value = false
            }
        }
    }

    fun createTask(task: ToDoItem) {
        _isTaskCreatingInProgress.value = true
        viewModelScope.launch {
            todoItemsRepository.createTask(revision = lastRevision, task = task)
        }
    }

    fun updateTask(taskId: String, task: ToDoItem) {
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