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

class TodoItemsRepository(
    private val context: Context,
) {

    private val todosApi = ApiService.todosApi(context = context)

    suspend fun getTodos(): Flow<RequestResult<List<ToDoItem>>> = flow {
        emit(RequestResult.Loading)
        try {
            val response = todosApi.getTodos().list.map { it.toToDoItem() }
            emit(RequestResult.Success(value = response))
        } catch (e: SocketTimeoutException) {
            emit(RequestResult.Failure(exception = Throwable(message = e.message)))
        } catch (e: Throwable) {
            emit(RequestResult.Failure(exception = e))
        }
    }

//    private val _tasksFlow: MutableStateFlow<List<ToDoItem>> = MutableStateFlow(emptyList())
//    val tasksFlow: StateFlow<List<ToDoItem>>
//        get() = _tasksFlow.asStateFlow()
//
//    private val _taskFlow: MutableStateFlow<ToDoItem> = MutableStateFlow(ToDoItem())
//    val taskFlow: StateFlow<ToDoItem>
//        get() = _taskFlow.asStateFlow()

//    suspend fun getTodos() {
//            val response = todosApi.getTodos().list.map { it.toToDoItem() }
//        _tasksFlow.value = response
//    }
//
//    suspend fun syncWithServer(revision: Int, tasks: List<ToDoItem>) {
//        val tasksToSync = SyncWithServerRequest(list = tasks.map { it.toTodoDTO() })
//        val response = todosApi.syncWithServer(
//            revision = revision,
//            tasks = tasksToSync
//        ).list.map { it.toToDoItem() }
//        _tasksFlow.updateAndGet { response }
//    }
//
//    suspend fun getTaskById(taskId: String): ToDoItem {
//        val task = todosApi.getTaskById(taskId = taskId).element.toToDoItem()
//        _taskFlow.value = task
//        return task
//    }
//
//    fun createTask(revision: Int, task: ToDoItem) {
//        val newTask = SaveTaskRequest(element = task.toTodoDTO())
//        val response = todosApi.createTask(revision = revision, task = newTask).element.toToDoItem()
//        _tasksFlow.updateAndGet { currentTasks -> currentTasks.plus(response) }
//    }
//
//    fun updateTask(taskId: String, task: ToDoItem) {
//        val taskToUpdate = SaveTaskRequest(element = task.toTodoDTO())
//        val response =
//            todosApi.updateTask(taskId = taskId, editedTask = taskToUpdate).element.toToDoItem()
//        _tasksFlow.updateAndGet { currentTasks ->
//            val targetTask =
//                requireNotNull(currentTasks.find { it.id == response.id }) { "Элеменет с id = \"${response.id}\" не существует в списке." }
//            val targetTaskIndex = currentTasks.indexOf(targetTask)
//            currentTasks.toMutableList().set(targetTaskIndex, targetTask)
//            currentTasks
//        }
//    }
//
//    fun deleteTask(taskId: String) {
//        val response = todosApi.deleteTask(taskId = taskId).element.toToDoItem()
//        _tasksFlow.updateAndGet { currentTasks -> currentTasks.minus(response) }
//        Result.success()
//    }
}