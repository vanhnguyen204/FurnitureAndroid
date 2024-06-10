package com.example.furniture.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.concurrent.TimeUnit
@RequiresApi(Build.VERSION_CODES.O)
fun calculateTimeDifference(previousTime: String): String {
    // Define the custom date format
    val formatter = DateTimeFormatter.ofPattern("yyyy-M-d H:mm:ss")
    val previous = LocalDateTime.parse(previousTime, formatter)
    val currentTime = LocalDateTime.now(ZoneId.systemDefault())

    val diffMinutes = ChronoUnit.MINUTES.between(previous, currentTime)
    val diffHours = ChronoUnit.HOURS.between(previous, currentTime)
    val diffDays = ChronoUnit.DAYS.between(previous, currentTime)
    return when {
        diffDays < 1 -> {
            when {
                diffMinutes < 1 -> "now"
                diffHours < 1 -> "${diffMinutes}m ago"
                else -> "${diffHours}h ago"
            }
        }
        else -> previousTime
    }
}