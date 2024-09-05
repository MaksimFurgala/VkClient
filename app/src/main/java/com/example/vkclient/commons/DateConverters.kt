package com.example.vkclient.commons

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateConverters {
    fun timestampToStringDate(timestamp: Long): String {
        val date = Date(timestamp)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}