package uz.futuresoft.tasks.presentation.task_events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.futuresoft.tasks.utils.TodoItemImportance
import uz.futuresoft.tasks.common.models.ToDoItemState
import uz.futuresoft.data.repositories.TodoItemsRepository2
import java.util.Calendar

class TaskDetailsViewModel(
    private val todoItemsRepository: TodoItemsRepository2,
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

    fun addTask(task: ToDoItemState) {
        todoItemsRepository.addTask(task = task)
    }

    fun getTaskById(id: String) {
        _task.value = todoItemsRepository.requireTaskById(id = id)
    }

    fun editTask(id: String, task: ToDoItemState) {
        todoItemsRepository.saveTask(id = id, task = task)
    }

    fun removeTask(id: String) {
        _loading.value = true
        viewModelScope.launch {
            delay(1000)
            todoItemsRepository.removeTask(id = id)
            _loading.value = false
        }
    }
}