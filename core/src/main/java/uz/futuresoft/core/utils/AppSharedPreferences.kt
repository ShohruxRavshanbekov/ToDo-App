package uz.futuresoft.core.utils

import android.content.Context
import android.content.SharedPreferences

object AppSharedPreferences {
    private const val NAME = "todoApp"
    const val KEY_THEME = "theme"
    const val KEY_REVISION = "theme"

    private lateinit var sharedPreferences: SharedPreferences

    fun create(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    fun write(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun write(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }
}