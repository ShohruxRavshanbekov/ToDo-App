package uz.futuresoft.network.models.request

data class SaveTaskRequest<T>(
    val status: String = "ok",
    val element: T,
)
