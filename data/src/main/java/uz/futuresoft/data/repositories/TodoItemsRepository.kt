package uz.futuresoft.data.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import uz.futuresoft.core.utils.RequestResult
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.toToDoItem
import uz.futuresoft.data.toTodoDTO
import uz.futuresoft.network.ApiService
import uz.futuresoft.network.models.request.SaveTaskRequest
import uz.futuresoft.network.models.request.SyncWithServerRequest
import java.net.SocketTimeoutException

class TodoItemsRepository(private val context: Context) {

    private val todosApi = ApiService.todosApi(context = context)

    private val _tasks = MutableStateFlow<List<ToDoItem>>(emptyList())
    val tasks: StateFlow<List<ToDoItem>>
        get() = _tasks.asStateFlow()

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?>
        get() = _error.asStateFlow()

//    suspend fun getTodos() {
//        try {
//            val response = todosApi.getTodos().list.map { it.toToDoItem() }
//            _tasks.value = Result.success(value = response)
//        } catch (e: Throwable) {
//            _tasks.value = Result.failure(exception = e)
//        }
//    }

    //    suspend fun getTodos() {
//        try {
//            val response = todosApi.getTodos().list.map { it.toToDoItem() }
//            _tasks.value = Result.success(value = response)
//        } catch (e: Throwable) {
//            _tasks.value = Result.failure(exception = e)
//        }
////        return try {
////            val response = todosApi.getTodos().list.map { it.toToDoItem() }
////            Result.success(value = response)
////        } catch (e: Throwable) {
////            Result.failure(exception = e)
////        }
//    }
    suspend fun getTodos() {
        val response = runCatching { todosApi.getTodos().list.map { it.toToDoItem() } }
        response
            .onSuccess { _tasks.value = it }
            .onFailure { _error.value = it }
    }

    suspend fun syncWithServer(revision: Int, tasks: List<ToDoItem>) {
        val tasksToSync = SyncWithServerRequest(list = tasks.map { it.toTodoDTO() })
        val response = runCatching {
            todosApi.syncWithServer(
                revision = revision,
                tasks = tasksToSync
            ).list.map { it.toToDoItem() }
        }
        response
            .onSuccess { _tasks.update { it } }
            .onFailure { _error.value = it }
    }

    suspend fun getTaskById(taskId: String): ToDoItem {
        val task = todosApi.getTaskById(taskId = taskId).element.toToDoItem()
//        _taskFlow.value = task
        return task
    }

    fun createTask(revision: Int, task: ToDoItem) {
        val newTask = SaveTaskRequest(element = task.toTodoDTO())
        val response = todosApi.createTask(revision = revision, task = newTask).element.toToDoItem()
//        _tasksFlow.updateAndGet { currentTasks -> currentTasks.plus(response) }
    }

    fun updateTask(taskId: String, task: ToDoItem): Result<ToDoItem> {
        val taskToUpdate = SaveTaskRequest(element = task.toTodoDTO())
        return try {
            val response =
                todosApi.updateTask(taskId = taskId, editedTask = taskToUpdate).element.toToDoItem()
            Result.success(value = response)
        } catch (e: Throwable) {
            Result.failure(exception = e)
        }

//        _tasksFlow.updateAndGet { currentTasks ->
//            val targetTask =
//                requireNotNull(currentTasks.find { it.id == response.id }) { "Элеменет с id = \"${response.id}\" не существует в списке." }
//            val targetTaskIndex = currentTasks.indexOf(targetTask)
//            currentTasks.toMutableList().set(targetTaskIndex, targetTask)
//            currentTasks
//        }
    }

    fun deleteTask(taskId: String): Result<ToDoItem> {
        return try {
            val response = todosApi.deleteTask(taskId = taskId).element.toToDoItem()
            Result.success(value = response)
        } catch (e: Throwable) {
            Result.failure(exception = e)
        }
//        _tasksFlow.updateAndGet { currentTasks -> currentTasks.minus(response) }
//        Result.success()
    }
}