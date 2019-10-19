package com.helicoptera.weatherforecast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.helicoptera.weatherforecast.data.db.entity.WEATHER_LOCATION_ID
import com.helicoptera.weatherforecast.data.db.entity.WeatherLocation

@Dao
interface WeatherLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherLocation: WeatherLocation)

    @Query("SELECT * FROM WEATHER_LOCATION WHERE ID = $WEATHER_LOCATION_ID")
    fun getLocation(): LiveData<WeatherLocation>
}