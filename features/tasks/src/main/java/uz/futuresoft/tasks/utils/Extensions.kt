package uz.futuresoft.tasks.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.formatDateMillisTo(pattern: String): String {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(Date(this))
}

fun Long.convertMillisToDate(): Date {
    return Date(this)
}