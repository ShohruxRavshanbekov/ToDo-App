package uz.futuresoft.tasks.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import java.net.SocketTimeoutException

class HomeViewModel(
    private val todoItemsRepository: TodoItemsRepository,
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading.asStateFlow()

    private val _error: MutableStateFlow<Throwable?> = MutableStateFlow(null)
    val error: StateFlow<Throwable?>
        get() = _error.asStateFlow()

    private val _tasks = MutableStateFlow<List<ToDoItem>>(emptyList())
    val tasks: StateFlow<List<ToDoItem>>
        get() = _tasks.asStateFlow()

    init {
//        viewModelScope.launch {
//            todoItemsRepository.tasks.collect { _tasks.value = it }
//        }
    }

    suspend fun getTasks() {
        _loading.value = true
        todoItemsRepository.getTodos()
    }

//    suspend fun getTasks() {
//        todoItemsRepository.getTodos()
////        _loading.value = true
////        val result = todoItemsRepository.getTodos()
////        when {
////            result.isSuccess -> {
////                val tasks = result.getOrNull() ?: emptyList()
////                _tasks.value = tasks
////            }
////
////            result.isFailure -> _error.value = result.exceptionOrNull()
////
////        }
////        _loading.value = false
//    }

    fun markAsCompleted(taskId: String, task: ToDoItem) {
//        _loading.value = true
//        val result = todoItemsRepository.updateTask(taskId = taskId, task = task)
//        when {
//            result.isSuccess -> {
//                _tasks.updateAndGet { }
//            }
//
//            result.isFailure -> _error.value = result.exceptionOrNull()
//        }
//        _loading.value = false
    }

    fun removeTask(taskId: String) {
//        _loading.value = true
//        val result = todoItemsRepository.deleteTask(taskId = taskId)
//        when {
//            result.isSuccess -> {
//                val removedTask
//                _tasks.updateAndGet { _tasks.value.minus() }
//            }
//
//            result.isFailure -> _error.value = result.exceptionOrNull()
//        }
//        _loading.value = false
    }
}