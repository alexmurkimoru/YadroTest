package com.example.yadrotest

import java.io.Serializable

data class DataSource(
    val locationName: String = "---",
    val locationLocalName: String? = null,
    val currentTemp: String = "---",
    val isDay: Boolean= true,
    val apparentTem: String= "---",
    val humidity: String= "---",
    val windSpeed: String= "---",
    val forecast: List<ForecastItem>? = null
) : Serializable

data class ForecastItem(
    val date: String = "",
    val minTemp: String = "",
    val maxTemp: String = ""
)