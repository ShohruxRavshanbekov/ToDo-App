package uz.futuresoft.network.models.request

import uz.futuresoft.network.models.TodoDTO

data class SyncWithServerRequest(
    val list: List<TodoDTO>,
    val status: String = "ok",
)
