package uz.futuresoft.tasks.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.futuresoft.tasks.domain.models.ToDoItem
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository

class HomeViewModel(
    private val toDoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<ToDoItem>>(emptyList())
    val tasks: StateFlow<List<ToDoItem>>
        get() = _tasks.asStateFlow()

    fun getTasks() = toDoItemsRepository.getTasks()

    fun removeTask(task: ToDoItem) {
        toDoItemsRepository.removeTask(task = task)
    }

    fun markAsCompleted(task: ToDoItem) {
        toDoItemsRepository.editTask(task = task)
    }
}