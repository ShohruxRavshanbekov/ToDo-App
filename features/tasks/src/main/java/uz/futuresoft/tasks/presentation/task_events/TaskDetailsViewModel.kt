package uz.futuresoft.tasks.presentation.task_events

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.futuresoft.core.utils.AppSharedPreferences
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.tasks.presentation.home.HomeUIState

class TaskDetailsViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskDetailsUIState())
    val uiState: StateFlow<TaskDetailsUIState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            todoItemsRepository.error.collect {
                Log.e(null, "ERROR:", it)
                _uiState.update { uiState -> uiState.copy(loading = false, error = it) }
            }
        }
    }

    fun getTaskById(id: String) {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            try {
                val task = todoItemsRepository.getTaskById(taskId = id)
                _uiState.update { it.copy(task = task) }
            } catch (e: Throwable) {
                Log.e(null, "ERROR:", e)
                _uiState.update { it.copy(error = e) }
            } finally {
                _uiState.update { it.copy(loading = false) }
            }
        }
    }

    fun createTask(task: ToDoItem) {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            todoItemsRepository.createTask(
                revision = AppSharedPreferences.getInt(key = AppSharedPreferences.KEY_REVISION),
                task = task
            )
        }
    }

    fun updateTask(taskId: String, task: ToDoItem) {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            todoItemsRepository.updateTask(
                revision = AppSharedPreferences.getInt(key = AppSharedPreferences.KEY_REVISION),
                taskId = taskId,
                task = task
            )
        }
    }

    fun removeTask(taskId: String) {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            todoItemsRepository.deleteTask(
                revision = AppSharedPreferences.getInt(key = AppSharedPreferences.KEY_REVISION),
                taskId = taskId
            )
        }
    }
}