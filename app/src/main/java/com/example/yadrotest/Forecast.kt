package com.example.yadrotest

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Forecast(
    @SerializedName("current")
    var currentInfo: CurrentInfo? = CurrentInfo(),
    @SerializedName("daily")
    var dailyInfo: DailyInfo? = DailyInfo()

)

data class CurrentInfo(
    @SerializedName("time") val time: String? = null,
    @SerializedName("temperature_2m") val temperature: Double? = null,
    @SerializedName("relative_humidity_2m") val relativeHumidity: Int? = null,
    @SerializedName("apparent_temperature") val apparentTemperature: Double? = null,
    @SerializedName("is_day") val isDay: Int? = null,
    @SerializedName("wind_speed_10m") val windSpeed10m: Double? = null
) : Serializable

data class DailyInfo(

    @SerializedName("time") var time: ArrayList<String> = arrayListOf(),
    @SerializedName("temperature_2m_max") val temperatureMax: ArrayList<Double> = arrayListOf(),
    @SerializedName("temperature_2m_min") val temperatureMin: ArrayList<Double> = arrayListOf(),

    ) : Serializable
