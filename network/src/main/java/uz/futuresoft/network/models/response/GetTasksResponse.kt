package uz.futuresoft.network.models.response

data class GetTasksResponse<T>(
    val list: T,
    val revision: Int,
    val status: String,
)
