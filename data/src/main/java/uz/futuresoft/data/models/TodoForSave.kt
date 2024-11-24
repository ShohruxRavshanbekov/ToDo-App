package uz.futuresoft.data.models

import java.util.Date

data class TodoForSave(
    val id: String,
    val text: String,
    val createdAt: Long,
    val importance: String,
    val isCompleted: Boolean,
    val deadline: Long? = null,
    val modifiedAt: Long? = null,
)
