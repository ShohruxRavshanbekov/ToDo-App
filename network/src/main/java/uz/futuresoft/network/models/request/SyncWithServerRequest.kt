package uz.futuresoft.network.models.request

data class SyncWithServerRequest<T>(
    val list: T,
    val status: String,
)
