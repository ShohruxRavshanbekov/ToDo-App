package uz.futuresoft.core.utils

sealed class RequestResult<out T> {
    data object Loading : RequestResult<Nothing>()
    data class Success<out T>(val value: T) : RequestResult<T>()
    data class Failure(val exception: Throwable) : RequestResult<Nothing>()

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isFailure() = this is Failure

    fun getOrNull(): T? = (this as? Success<T>)?.value
    fun getExceptionOrNull(): Throwable? = (this as? Failure)?.exception
}