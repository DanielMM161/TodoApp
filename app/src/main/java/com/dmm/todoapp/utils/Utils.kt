package com.dmm.todoapp.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun convertDateToFormattedDate(date: Date): String {
        val formatted = SimpleDateFormat("dd/M/yyyy")
        return formatted.format(date)
    }
}