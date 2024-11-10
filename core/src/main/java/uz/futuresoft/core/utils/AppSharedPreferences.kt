package uz.futuresoft.core.utils

import android.content.Context
import android.content.SharedPreferences

object AppSharedPreferences {
    private const val NAME = "todoApp"
    const val KEY_THEME = "theme"

    private lateinit var sharedPreferences: SharedPreferences

    fun create(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    fun write(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun get(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}