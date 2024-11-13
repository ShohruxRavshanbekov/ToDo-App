package uz.futuresoft.tasks.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.tasks.common.models.ToDoItemState
import uz.futuresoft.tasks.utils.TodoItemImportance
import uz.futuresoft.tasks.utils.toTodoItem
import uz.futuresoft.tasks.utils.toTodoItemState
import java.util.Calendar

class HomeViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<ToDoItemState>>(emptyList())
    val tasks: StateFlow<List<ToDoItemState>>
        get() = _tasks.asStateFlow()

    private val _taskRequestResult = MutableStateFlow(
        ToDoItemState(
            id = "",
            text = "",
            importance = TodoItemImportance.NORMAL,
            isCompleted = false,
            createdAt = Calendar.getInstance().time,
        )
    )
    val taskRequestResult: StateFlow<ToDoItemState>
        get() = _taskRequestResult.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                todoItemsRepository.tasksFlow.collect { tasks ->
                    _tasks.value = tasks.map { it.toTodoItemState() }
                }
            } catch (e: Exception) {

            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            todoItemsRepository.taskRequestFlow.collect { task ->
                _taskRequestResult.value = task.toTodoItemState()
            }
        }
    }

    suspend fun getTasks() {
        todoItemsRepository.getTodos()
    }

    fun markAsCompleted(taskId: String, task: ToDoItemState) {
        todoItemsRepository.updateTask(taskId = taskId, task = task.toTodoItem())
    }

    fun removeTask(taskId: String) {
        todoItemsRepository.deleteTask(taskId = taskId)
    }
}