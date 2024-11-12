package uz.futuresoft.data.repositories

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.toToDoItem
import uz.futuresoft.data.toTodoDTO
import uz.futuresoft.network.ApiService
import uz.futuresoft.network.models.request.SaveTaskRequest
import uz.futuresoft.network.models.request.SyncWithServerRequest

class TodoItemsRepository {

    private val todosApi = ApiService.todosApi

    private val _flow = MutableStateFlow(listOf<ToDoItem>())
    val flow: StateFlow<List<ToDoItem>>
        get() = _flow.asStateFlow()

    suspend fun getTodos() {
        val response = todosApi.getTodos().list.map { it.toToDoItem() }
        _flow.value = response
    }

    suspend fun syncWithServer(revision: Int, tasks: List<ToDoItem>) {
        val header = mapOf("X-Last-Known-Revision" to revision)
        val tasksToSync = SyncWithServerRequest(list = tasks.map { it.toTodoDTO() })
        val response = todosApi.syncWithServer(
            header = header,
            tasks = tasksToSync
        ).list.map { it.toToDoItem() }
        _flow.update { response }
    }

    suspend fun getTaskById(taskId: String): ToDoItem {
//        val response = todosApi.getTaskById(taskId = taskId).element.toToDoItem()
        return todosApi.getTaskById(taskId = taskId).element.toToDoItem()
    }

    fun createTask(revision: Int, task: ToDoItem): ToDoItem {
        val header = mapOf("X-Last-Known-Revision" to revision)
        val newTask = SaveTaskRequest(element = task.toTodoDTO())
//        val response = todosApi.createTask(header = header, task = newTask).element.toToDoItem()
        return todosApi.createTask(header = header, task = newTask).element.toToDoItem()
    }

    fun updateTask(taskId: String, task: ToDoItem): ToDoItem {
        val taskToUpdate = SaveTaskRequest(element = task.toTodoDTO())
//        val response = todosApi.updateTask(taskId = taskId, editedTask = taskToUpdate).element.toToDoItem()
        return todosApi.updateTask(taskId = taskId, editedTask = taskToUpdate).element.toToDoItem()
    }

    fun deleteTask(taskId: String): ToDoItem {
//        val response = todosApi.deleteTask(taskId = taskId).element.toToDoItem()
        return todosApi.deleteTask(taskId = taskId).element.toToDoItem()
    }
}