package uz.futuresoft.tasks.presentation.task_events

import androidx.lifecycle.ViewModel
import uz.futuresoft.tasks.domain.models.TodoItem
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository

class TaskDetailsViewModel(
    private val toDoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    fun addTask(task: TodoItem) {
        toDoItemsRepository.addTask(task = task)
    }

    fun editTask(task: TodoItem) {
        toDoItemsRepository.saveTask(task = task)
    }

    fun removeTask(task: TodoItem) {
        toDoItemsRepository.removeTask(task = task)
    }
}