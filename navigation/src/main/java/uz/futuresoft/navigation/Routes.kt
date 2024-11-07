package uz.futuresoft.navigation

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object Home

    @Serializable
    data class TaskEvents(val taskId: String? = null)
}