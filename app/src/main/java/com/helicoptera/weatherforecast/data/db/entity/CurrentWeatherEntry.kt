package com.helicoptera.weatherforecast.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "CURRENT_WEATHER")
data class CurrentWeatherEntry(
    val feelslike: Int,
    @SerializedName("is_day")
    val isDay: String,
    val precip: Int,
    val temperature: Int,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("uv_index")
    val visibility: Int,
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
    @SerializedName("weather_icons")
    val weatherIcons: List<String>,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_speed")
    val windSpeed: Int
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}