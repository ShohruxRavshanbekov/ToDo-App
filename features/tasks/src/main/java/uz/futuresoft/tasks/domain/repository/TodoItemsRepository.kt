package uz.futuresoft.tasks.domain.repository

import uz.futuresoft.tasks.common.models.TodoItemImportance
import uz.futuresoft.tasks.domain.models.ToDoItem
import java.util.Calendar
import java.util.UUID

class TodoItemsRepository {

    fun getTodos(): List<ToDoItem> = tasks.sortedByDescending { it.createdAt }

    fun requireTaskById(id: String): ToDoItem =
        requireNotNull(tasks.find { it.id == id }) { "Значение с id = $id не существует!" }

    fun addTask(task: ToDoItem) {
        tasks.add(task)
    }

    fun saveTask(id: String, task: ToDoItem) {
        val targetTask = requireTaskById(id = id)
        val index = tasks.indexOf(targetTask)
        tasks[index] = task
    }

    fun removeTask(id: String) {
        val targetTask = requireTaskById(id = id)
        tasks.remove(targetTask)
    }

    private val tasks = mutableListOf(
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Делать уроки",
            importance = TodoItemImportance.NORMAL,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Играть футбол",
            importance = TodoItemImportance.LOW,
            isCompleted = true,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Посещать лекцию Яндекса :)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Прочитать статью про Kotlin-Coroutines",
            importance = TodoItemImportance.HIGH,
            isCompleted = true,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Посмотреть урок по Flutter. (Widgets)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Делать уроки",
            importance = TodoItemImportance.NORMAL,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Играть футбол",
            importance = TodoItemImportance.LOW,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Посещать лекцию Яндекса :)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Прочитать статью про Kotlin-Coroutines",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Посмотреть урок по Flutter. (Widgets)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Делать уроки",
            importance = TodoItemImportance.NORMAL,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Играть футбол",
            importance = TodoItemImportance.LOW,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Посещать лекцию Яндекса :)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Прочитать статью про Kotlin-Coroutines",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        ToDoItem(
            id = UUID.randomUUID().toString(),
            text = "Посмотреть урок по Flutter. (Widgets)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
    )
}