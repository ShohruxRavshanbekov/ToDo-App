package uz.futuresoft.tasks.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.futuresoft.core.utils.AppSharedPreferences
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository

class HomeViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            todoItemsRepository.tasks.collect {
                _uiState.update { uiState ->
                    uiState.copy(
                        loading = false,
                        tasks = it.sortedByDescending { it.createdAt },
                    )
                }
            }
        }

        viewModelScope.launch {
            todoItemsRepository.error.collect {
                Log.e(null, "ERROR:", it)
                _uiState.update { uiState -> uiState.copy(loading = false, error = it) }
            }
        }
    }

    suspend fun getTasks() {
        _uiState.update { it.copy(loading = true) }
        todoItemsRepository.getTodosFromLocalDatabase()
    }

    fun markAsCompleted(taskId: String, task: ToDoItem) {
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