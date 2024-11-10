package uz.futuresoft.navigation

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object Home

    @Serializable
    data class TaskDetails(val taskId: String? = null)
}