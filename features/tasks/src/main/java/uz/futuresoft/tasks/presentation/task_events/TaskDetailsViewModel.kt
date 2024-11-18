package uz.futuresoft.tasks.presentation.task_events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.tasks.utils.TodoItemImportance
import java.util.Calendar

class TaskDetailsViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _gettingTaskInProgress = MutableStateFlow(false)
    val gettingTaskInProgress: StateFlow<Boolean>
        get() = _gettingTaskInProgress.asStateFlow()

    private val _createTaskInProgress = MutableStateFlow(false)
    val createTaskInProgress: StateFlow<Boolean>
        get() = _createTaskInProgress.asStateFlow()

    private val _updateTaskInProgress = MutableStateFlow(false)
    val updateTaskInProgress: StateFlow<Boolean>
        get() = _updateTaskInProgress.asStateFlow()

    private val _deletingTaskInProgress = MutableStateFlow(false)
    val deletingTaskInProgress: StateFlow<Boolean>
        get() = _deletingTaskInProgress.asStateFlow()

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?>
        get() = _error.asStateFlow()

    private val _task = MutableStateFlow(ToDoItem())
    val task: StateFlow<ToDoItem>
        get() = _task.asStateFlow()

    init {
        viewModelScope.launch {
            todoItemsRepository.task.collect {
                _task.value = it
                _gettingTaskInProgress.value = false
                _createTaskInProgress.value = false
                _updateTaskInProgress.value = false
                _deletingTaskInProgress.value = false
            }
        }

        viewModelScope.launch {
            todoItemsRepository.error.collect {
                _error.value = it
                _gettingTaskInProgress.value = false
                _createTaskInProgress.value = false
                _updateTaskInProgress.value = false
                _deletingTaskInProgress.value = false
            }
        }
    }

    suspend fun getTaskById(id: String) {
        _gettingTaskInProgress.value = true
        return todoItemsRepository.getTaskById(taskId = id)
    }

    suspend fun createTask(revision: Int, task: ToDoItem) {
        _createTaskInProgress.value = true
        todoItemsRepository.createTask(revision = revision, task = task)
    }

    suspend fun updateTask(revision: Int, taskId: String, task: ToDoItem) {
        _updateTaskInProgress.value = true
        todoItemsRepository.updateTask(revision = revision, taskId = taskId, task = task)
    }

    suspend fun removeTask(revision: Int, taskId: String) {
        _deletingTaskInProgress.value = true
        todoItemsRepository.deleteTask(revision = revision, taskId = taskId)
    }
}