package uz.futuresoft.data.repositories

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uz.futuresoft.core.utils.AppSharedPreferences
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.toToDoItem
import uz.futuresoft.data.toTodoDTO
import uz.futuresoft.data.toTodoEntity
import uz.futuresoft.data.toTodoItem
import uz.futuresoft.local.localDatabase
import uz.futuresoft.network.ApiService
import uz.futuresoft.network.models.request.SaveTaskRequest
import uz.futuresoft.network.models.request.SyncWithServerRequest

class TodoItemsRepository(private val context: Context) {

    private val todosApi = ApiService.todosApi(context = context)
    private val localDatabase = localDatabase(context = context)

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?>
        get() = _error.asStateFlow()

    private val _tasks = MutableStateFlow<List<ToDoItem>>(emptyList())
    val tasks: StateFlow<List<ToDoItem>>
        get() = _tasks.asStateFlow()

    suspend fun getTodosFromLocalDatabase() {
        getTodos()
        val result = runCatching { localDatabase.todoDao.getAllTodos() }
        result
            .onSuccess {
                _tasks.value = emptyList()
                _tasks.value = it.map { todoEntity -> todoEntity.toTodoItem() }
            }
            .onFailure { _error.value = it }
    }

    private suspend fun getTodos() {
        val result = runCatching {
            val response = todosApi.getTodos()
            AppSharedPreferences.write(
                key = AppSharedPreferences.KEY_REVISION,
                value = response.revision
            )
            response.list.map { it.toToDoItem() }
        }
        result
            .onSuccess { todosFromServer ->
                localDatabase.todoDao.insertTodos(todos = todosFromServer.map { todoItem -> todoItem.toTodoEntity() })
                _tasks.update { todosFromServer }
            }
            .onFailure { _error.value = it }
    }

    private suspend fun syncWithServer(revision: Int, tasks: List<ToDoItem>) {
        val result = runCatching {
            val tasksToSync = SyncWithServerRequest(list = tasks.map { it.toTodoDTO() })
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
            .onSuccess { localDatabase.todoDao.insertTodos(todos = it.map { todoItem -> todoItem.toTodoEntity() }) }
            .onFailure { _error.value = it }
    }

    suspend fun getTaskById(taskId: String): ToDoItem {
        return localDatabase.todoDao.getTodoById(id = taskId).toTodoItem()
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
            }
            .onFailure { _error.value = it }
    }

    suspend fun updateTask(revision: Int, taskId: String, task: ToDoItem) {
        val todoDao = localDatabase.todoDao
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
                todoDao.updateTodo(todo = updatedTask.toTodoEntity())
            }
            .onFailure { _error.value = it }
    }

    suspend fun deleteTask(revision: Int, taskId: String) {
        val todoDao = localDatabase.todoDao
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
                todoDao.deleteTodo(todo = deletedTask.toTodoEntity())
            }
            .onFailure { _error.value = it }
    }
}