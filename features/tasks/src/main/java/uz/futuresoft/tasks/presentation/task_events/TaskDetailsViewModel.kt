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

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading.asStateFlow()

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?>
        get() = _error.asStateFlow()

    private val _task = MutableStateFlow(
        ToDoItem(
            id = "",
            text = "",
            createdAt = Calendar.getInstance().timeInMillis,
            importance = TodoItemImportance.NORMAL.value,
            isCompleted = false
        )
    )
    val task: StateFlow<ToDoItem>
        get() = _task.asStateFlow()

//    init {
//        _loading.value = true
//        viewModelScope.launch {
//            try {
//                todoItemsRepository.tasksFlow.collect {}
//            } catch (e: Throwable) {
//                _error.value = e
//            } finally {
//                _loading.value = false
//            }
//        }
//    }

    suspend fun getTaskById(id: String): ToDoItem {
        return todoItemsRepository.getTaskById(taskId = id)
    }

    fun createTask(revision: Int, task: ToDoItem) {
        todoItemsRepository.createTask(revision = revision, task = task)
    }

    fun updateTask(taskId: String, task: ToDoItem) {
        todoItemsRepository.updateTask(taskId = taskId, task = task)
    }

    fun removeTask(taskId: String) {
        todoItemsRepository.deleteTask(taskId = taskId)
    }
}