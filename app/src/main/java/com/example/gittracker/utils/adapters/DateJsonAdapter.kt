package com.example.gittracker.utils.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateJsonAdapter {
    private val timeZone = TimeZone.getTimeZone("UTC")

    private val dateFormat: DateFormat = SimpleDateFormat(
        DATE_FORMAT_ISO_8601, // Quoted "Z" indicates UTC, no timezone offset
        Locale.ENGLISH,
    ).apply {
        this.timeZone = timeZone
    }

    @ToJson
    fun toJson(date: Date): String {
        return dateFormat.format(date)
    }

    @FromJson
    fun fromJson(date: String): Date {
        return requireNotNull(dateFormat.parse(date)) {
            "Unable to parse date from json string date: $date"
        }
    }

    companion object {
        private const val DATE_FORMAT_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }
}
