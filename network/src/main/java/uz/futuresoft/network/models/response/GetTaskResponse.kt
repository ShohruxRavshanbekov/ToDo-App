package uz.futuresoft.network.models.response

data class GetTaskResponse<T>(
    val element: T,
    val revision: Int,
    val status: String,
)
