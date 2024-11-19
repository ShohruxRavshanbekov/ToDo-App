package uz.futuresoft.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey
    val id: String,
    val text: String,
    val importance: String,
    val deadline: Long? = null,
    val isCompleted: Boolean,
    val createdAt: Long,
    val modifiedAt: Long?,
)
