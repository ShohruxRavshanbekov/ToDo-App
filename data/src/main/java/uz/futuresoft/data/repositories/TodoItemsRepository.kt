package uz.futuresoft.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.toToDoItem
import uz.futuresoft.data.toTodoDTO
import uz.futuresoft.network.ApiService
import uz.futuresoft.network.models.request.SaveTaskRequest
import uz.futuresoft.network.models.request.SyncWithServerRequest

class TodoItemsRepository {

    private val todosApi = ApiService.todosApi

    suspend fun getTodos(): List<ToDoItem> {
        return todosApi.getTodos().list.map { it.toToDoItem() }
    }

    suspend fun syncWithServer(revision: Int, tasks: List<ToDoItem>): List<ToDoItem> {
        return todosApi.syncWithServer(
            header = mapOf("X-Last-Known-Revision" to revision),
            tasks = SyncWithServerRequest(list = tasks.map { it.toTodoDTO() })
        ).list.map { it.toToDoItem() }
    }

    suspend fun getTaskById(taskId: String): ToDoItem {
        return todosApi.getTaskById(taskId = taskId).element.toToDoItem()
    }

    fun addTask(revision: Int, task: ToDoItem): ToDoItem {
        return todosApi.addTask(
            header = mapOf("X-Last-Known-Revision" to revision),
            task = SaveTaskRequest(element = task.toTodoDTO())
        ).element.toToDoItem()
    }

    fun saveTask(id: String, task: ToDoItem): ToDoItem {
        return todosApi.saveTask(
            taskId = id,
            editedTask = SaveTaskRequest(element = task.toTodoDTO())
        ).element.toToDoItem()
    }
}