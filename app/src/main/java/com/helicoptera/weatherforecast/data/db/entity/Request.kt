package com.helicoptera.weatherforecast.data.db.entity


data class Request(
    val language: String,
    val query: String,
    val type: String,
    val unit: String
)