package uz.futuresoft.network.models.request

import com.google.gson.annotations.SerializedName

data class NewTodo(
    @SerializedName("id") val id: String,
    @SerializedName("changed_at") val changedAt: Long? = null,
    @SerializedName("color") val color: String? = null,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("deadline") val deadline: Long? = null,
    @SerializedName("done") val done: Boolean = false,
    @SerializedName("files") val files: Any? = null,
    @SerializedName("importance") val importance: String,
    @SerializedName("last_updated_by") val lastUpdatedBy: String? = null,
    @SerializedName("text") val text: String,
)