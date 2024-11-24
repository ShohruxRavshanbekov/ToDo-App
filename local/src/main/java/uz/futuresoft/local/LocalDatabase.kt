package uz.futuresoft.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

class LocalDatabase internal constructor(private val database: TodosDatabase) {
    val todoDao: TodoDao
        get() = database.todoDao()
}

@Database(entities = [TodoEntity::class], version = 1)
internal abstract class TodosDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

fun localDatabase(context: Context): LocalDatabase {
    val todosDatabase = Room.databaseBuilder(
        context = context,
        klass = TodosDatabase::class.java,
        name = "local database"
    ).build()
    return LocalDatabase(database = todosDatabase)
}