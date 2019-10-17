package com.helicoptera.weatherforecast.data.unitlocalized

interface UnitSpecificCurrentWeatherEntry{
    val feelslike: Int
    val isDay: String
    val precip: Int
    val temperature: Int
    val weatherCode: Int
    val visibility: Int
    val weatherDescriptions: List<String>
    val weatherIcons: List<String>
    val windDir: String
    val windSpeed: Int
}