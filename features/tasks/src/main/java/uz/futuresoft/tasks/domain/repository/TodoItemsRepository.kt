package uz.futuresoft.tasks.domain.repository

import uz.futuresoft.tasks.common.models.TodoItemImportance
import uz.futuresoft.tasks.domain.models.TodoItem
import java.util.Calendar
import java.util.UUID

class TodoItemsRepository {
    fun getTodos(): List<TodoItem> = tasks

    fun requireTaskById(id: String): TodoItem =
        requireNotNull(tasks.find { it.id == id }) { "Значение с id=$id не существует!" }

    fun addTask(task: TodoItem) {
        tasks.add(task)
    }

    fun saveTask(task: TodoItem) {
        val index = tasks.indexOf(task)
        tasks.add(index = index, element = task)
    }

    fun removeTask(task: TodoItem) {
        tasks.remove(task)
    }

    val tasks = mutableListOf(
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Делать уроки",
            importance = TodoItemImportance.NORMAL,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Играть футбол",
            importance = TodoItemImportance.LOW,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Посещать лекцию Яндекса :)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Прочитать статью про Kotlin-Coroutines",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Посмотреть урок по Flutter. (Widgets)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Делать уроки",
            importance = TodoItemImportance.NORMAL,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Играть футбол",
            importance = TodoItemImportance.LOW,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Посещать лекцию Яндекса :)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Прочитать статью про Kotlin-Coroutines",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Посмотреть урок по Flutter. (Widgets)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Делать уроки",
            importance = TodoItemImportance.NORMAL,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Играть футбол",
            importance = TodoItemImportance.LOW,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Посещать лекцию Яндекса :)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Прочитать статью про Kotlin-Coroutines",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
        TodoItem(
            id = UUID.randomUUID().toString(),
            text = "Посмотреть урок по Flutter. (Widgets)",
            importance = TodoItemImportance.HIGH,
            isCompleted = false,
            createdAt = Calendar.getInstance().time
        ),
    )
}