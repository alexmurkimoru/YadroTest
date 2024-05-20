package com.example.yadrotest

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateConverter {

    fun convertDate(date: String): String {
        val formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd")
            .withLocale(Locale("ru", "RU"))

        val d = LocalDate.parse(date, formatter)

        val outFormatter = DateTimeFormatter
            .ofPattern("dd MMMM")
            .withLocale(Locale("ru", "RU"))
        return d.format(outFormatter)
    }
}