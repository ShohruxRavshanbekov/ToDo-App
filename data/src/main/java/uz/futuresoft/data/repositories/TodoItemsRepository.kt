package uz.futuresoft.data.repositories

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uz.futuresoft.core.utils.AppSharedPreferences
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.toToDoItem
import uz.futuresoft.data.toTodoDTO
import uz.futuresoft.network.ApiService
import uz.futuresoft.network.models.request.SaveTaskRequest
import uz.futuresoft.network.models.request.SyncWithServerRequest

class TodoItemsRepository(private val context: Context) {

    private val todosApi = ApiService.todosApi(context = context)

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?>
        get() = _error.asStateFlow()

    private val _tasks = MutableStateFlow<List<ToDoItem>>(emptyList())
    val tasks: StateFlow<List<ToDoItem>>
        get() = _tasks.asStateFlow()

    private val _taskModificationResponse = MutableStateFlow(false)
    val taskModificationResponse: StateFlow<Boolean>
        get() = _taskModificationResponse.asStateFlow()


    suspend fun getTodos() {
        val result = runCatching {
            val response = todosApi.getTodos()
            AppSharedPreferences.write(
                key = AppSharedPreferences.KEY_REVISION,
                value = response.revision
            )
            response.list.map { it.toToDoItem() }
        }
        result
            .onSuccess {
                _tasks.value = emptyList()
                _tasks.value = it
            }
            .onFailure { _error.value = it }
    }

    suspend fun syncWithServer(revision: Int, tasks: List<ToDoItem>) {
        val tasksToSync = SyncWithServerRequest(list = tasks.map { it.toTodoDTO() })
        val result = runCatching {
            val response = todosApi.syncWithServer(
                revision = revision,
                tasks = tasksToSync
            )
            AppSharedPreferences.write(
                key = AppSharedPreferences.KEY_REVISION,
                value = response.revision
            )
            response.list.map { it.toToDoItem() }
        }
        result
            .onSuccess { _tasks.update { it } }
            .onFailure { _error.value = it }
    }

    suspend fun getTaskById(taskId: String): ToDoItem {
        return todosApi.getTaskById(taskId = taskId).element.toToDoItem()
    }

    suspend fun createTask(revision: Int, task: ToDoItem) {
        val newTask = SaveTaskRequest(element = task.toTodoDTO())
        val result = runCatching {
            val response = todosApi.createTask(
                revision = revision,
                task = newTask,
            )
            AppSharedPreferences.write(
                key = AppSharedPreferences.KEY_REVISION,
                value = response.revision
            )
            response.element.toToDoItem()
        }
        result
            .onSuccess { createdTask ->
                _tasks.update { currentTasks -> currentTasks.plus(createdTask) }
                _taskModificationResponse.value = true
            }
            .onFailure { _error.value = it }
    }

    suspend fun updateTask(revision: Int, taskId: String, task: ToDoItem) {
        val taskToUpdate = SaveTaskRequest(element = task.toTodoDTO())
        val result = runCatching {
            val response = todosApi.updateTask(
                revision = revision,
                taskId = taskId,
                taskToUpdate = taskToUpdate,
            )
            AppSharedPreferences.write(
                key = AppSharedPreferences.KEY_REVISION,
                value = response.revision
            )
            response.element.toToDoItem()
        }
        result
            .onSuccess { updatedTask ->
                _tasks.update { currentTasks ->
                    currentTasks.map {
                        if (updatedTask.id == it.id) {
                            updatedTask
                        } else {
                            it
                        }
                    }
                }
                _taskModificationResponse.value = true
            }
            .onFailure { _error.value = it }
    }

    suspend fun deleteTask(revision: Int, taskId: String) {
        val result = runCatching {
            val response = todosApi.deleteTask(
                revision = revision,
                taskId = taskId
            )
            AppSharedPreferences.write(
                key = AppSharedPreferences.KEY_REVISION,
                value = response.revision
            )
            response.element.toToDoItem()
        }
        result
            .onSuccess { deletedTask ->
                _tasks.update { currentTasks -> currentTasks.minus(deletedTask) }
                _taskModificationResponse.value = true
            }
            .onFailure { _error.value = it }
    }
}