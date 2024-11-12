package uz.futuresoft.network.models.request

import uz.futuresoft.network.models.TodoDTO

data class SaveTaskRequest(
    val status: String = "ok",
    val element: TodoDTO,
)
