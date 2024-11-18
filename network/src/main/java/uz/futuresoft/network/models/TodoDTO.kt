package uz.futuresoft.network.models

import com.google.gson.annotations.SerializedName

data class TodoDTO(
    @SerializedName("id") val id: String? = null,
    @SerializedName("text") val text: String? = null,
    @SerializedName("importance") val importance: String? = null,
    @SerializedName("deadline") val deadline: Long? = null,
    @SerializedName("done") val done: Boolean? = null,
    @SerializedName("color") val color: String? = "",
    @SerializedName("created_at") val createdAt: Long? = null,
    @SerializedName("changed_at") val changedAt: Long? = null,
    @SerializedName("last_updated_by") val lastUpdatedBy: String? = "",
)