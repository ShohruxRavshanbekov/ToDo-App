package uz.futuresoft.tasks.presentation.task_events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.tasks.common.models.ToDoItemState
import uz.futuresoft.tasks.utils.TodoItemImportance
import uz.futuresoft.tasks.utils.toTodoItem
import java.util.Calendar

class TaskDetailsViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading.asStateFlow()

    private val _task = MutableStateFlow(
        ToDoItemState(
            id = "",
            text = "",
            createdAt = Calendar.getInstance().time,
            importance = TodoItemImportance.NORMAL,
            isCompleted = false
        )
    )
    val task: StateFlow<ToDoItemState>
        get() = _task.asStateFlow()

    suspend fun getTaskById(id: String) {
        todoItemsRepository.getTaskById(taskId = id)
    }

    fun createTask(revision: Int, task: ToDoItemState) {
        todoItemsRepository.createTask(revision = revision, task = task.toTodoItem())
    }

    fun updateTask(taskId: String, task: ToDoItemState) {
        todoItemsRepository.updateTask(taskId = taskId, task = task.toTodoItem())
    }

    fun removeTask(taskId: String) {
        todoItemsRepository.deleteTask(taskId = taskId)
    }
}