package uz.futuresoft.tasks.presentation.task_events

import androidx.lifecycle.ViewModel
import uz.futuresoft.tasks.domain.models.ToDoItem
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository

class TaskEventViewModel(
    private val toDoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    fun addTask(task: ToDoItem) {
        toDoItemsRepository.addTask(task = task)
    }

    fun editTask(task: ToDoItem) {
        toDoItemsRepository.editTask(task = task)
    }

    fun removeTask(task: ToDoItem) {
        toDoItemsRepository.removeTask(task = task)
    }
}