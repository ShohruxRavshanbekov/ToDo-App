package uz.futuresoft.network.models.response

import com.google.gson.annotations.SerializedName

data class TodoDTO(
    @SerializedName("id") val id: String? = null,
    @SerializedName("changed_at") val changedAt: Long? = null,
    @SerializedName("color") val color: String? = null,
    @SerializedName("created_at") val createdAt: Long? = null,
    @SerializedName("deadline") val deadline: Long? = null,
    @SerializedName("done") val done: Boolean? = null,
    @SerializedName("files") val files: Any? = null,
    @SerializedName("importance") val importance: String? = null,
    @SerializedName("last_updated_by") val lastUpdatedBy: String? = null,
    @SerializedName("text") val text: String? = null,
)