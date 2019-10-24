package com.helicoptera.weatherforecast.data.provider

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.helicoptera.weatherforecast.data.db.entity.WeatherLocation
import com.helicoptera.weatherforecast.internal.LocationPermissionNotGrantedException
import com.helicoptera.weatherforecast.internal.asDeffered
import kotlinx.coroutines.Deferred
import kotlin.math.abs

const val USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION"
const val CUSTOM_LOCATION = "CUSTOM_LOCATION"

class LocationProviderImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    context: Context
) : PreferenceProvider(context), LocationProvider {

    //зачем контекст если он есть в провайдере

    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        val deviceLocationChanged = try {
            hasDeviceLocationChanged(lastWeatherLocation)
        } catch (e: LocationPermissionNotGrantedException) {
            return false
        }
        return deviceLocationChanged || hasCustomLocationChanged(lastWeatherLocation)
    }

    private fun hasCustomLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
       if (!isUsingDeviceLocation()) {
           val customLocationName = getCustomLocationName()
           return customLocationName != lastWeatherLocation.name
       }
        return false
    }

    private fun getCustomLocationName(): String? {
        return preferences.getString(CUSTOM_LOCATION, null)
    }

    private suspend fun hasDeviceLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        if (!isUsingDeviceLocation()) {
            return false
        }

        val deviceLocation = getLastDeviceLocation().await()
            ?: return false

        val comparisonThreshold = 0.03
        return (abs(deviceLocation.latitude - lastWeatherLocation.lat.toDouble())
                > comparisonThreshold) &&
                (abs(deviceLocation.latitude - lastWeatherLocation.lon.toDouble())
                        > comparisonThreshold)
    }

    private fun isUsingDeviceLocation(): Boolean {
        return preferences.getBoolean(USE_DEVICE_LOCATION, true)
    }

    private fun getLastDeviceLocation(): Deferred<Location?> {
        return if (hasLocationPermission())
            fusedLocationProviderClient.lastLocation.asDeffered()
        else
            throw LocationPermissionNotGrantedException()
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            appContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override suspend fun getPreferredLocationString(): String {
        if (isUsingDeviceLocation()) {
            try {
                val deviceLocation = getLastDeviceLocation().await()
                    ?: return "${getCustomLocationName()}"
                return "${deviceLocation.latitude},${deviceLocation.longitude}"
            } catch (e: LocationPermissionNotGrantedException) {
                return "${getCustomLocationName()}"
            }
        } else {
            return "${getCustomLocationName()}"
        }
    }

}