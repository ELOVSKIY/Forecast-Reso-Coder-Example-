package com.helicoptera.weatherforecast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.helicoptera.weatherforecast.data.db.entity.CURRENT_WEATHER_ID
import com.helicoptera.weatherforecast.data.db.entity.CurrentWeatherEntry
import com.helicoptera.weatherforecast.data.unitlocalized.UnitCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("SELECT * FROM CURRENT_WEATHER WHERE ID = $CURRENT_WEATHER_ID")
    fun getWeatherUnit(): LiveData<UnitCurrentWeatherEntry>
}