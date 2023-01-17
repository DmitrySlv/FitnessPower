package com.example.fitnesspower.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

object TimeUtils {
    @SuppressLint("SimpleDateFormat")
    val formatter = SimpleDateFormat("mm:ss")

    fun getTime(time: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        return formatter.format(calendar.time)
    }
}