package com.example.vkclient.commons

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateConverters {

    private const val MILLISECONDS_PER_SECOND = 1000

    fun timestampToStringDate(timestamp: Long): String {
        val date = Date(timestamp * MILLISECONDS_PER_SECOND)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }
}