package com.olivia.plant.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun String.toReadableDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    } catch (e: Exception) {
        "Invalid date format"
    }
}

fun String.isDateToday(): Boolean {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val today = Calendar.getInstance().time

    return try {
        val date = dateFormat.parse(this)
        dateFormat.format(date) == dateFormat.format(today)
    } catch (e: Exception) {
        false
    }
}
