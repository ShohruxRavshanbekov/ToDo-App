package uz.futuresoft.tasks.domain.repository

import uz.futuresoft.tasks.domain.models.ToDoItem
import uz.futuresoft.tasks.utils.tasks

class TodoItemsRepository {

    fun getTasks(): List<ToDoItem> = tasks

    fun getTask(id: String): ToDoItem = tasks.find { it.id == id }!!

    fun addTask(task: ToDoItem) {
        tasks.add(task)
    }

    fun editTask(task: ToDoItem) {
        val index = tasks.indexOf(task)
        tasks.add(index = index, element = task)
    }

    fun removeTask(task: ToDoItem) {
        tasks.remove(task)
    }
}