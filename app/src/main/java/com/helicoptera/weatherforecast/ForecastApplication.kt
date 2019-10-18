package com.helicoptera.weatherforecast

import android.app.Application
import androidx.preference.PreferenceManager
import com.helicoptera.weatherforecast.data.WeatherStackApiService
import com.helicoptera.weatherforecast.data.db.ForecastDatabase
import com.helicoptera.weatherforecast.data.network.ConnectivityInterceptor
import com.helicoptera.weatherforecast.data.network.ConnectivityInterceptorImpl
import com.helicoptera.weatherforecast.data.network.WeatherNetworkDataSource
import com.helicoptera.weatherforecast.data.network.WeatherNetworkDataSourceImpl
import com.helicoptera.weatherforecast.data.provider.UnitProvider
import com.helicoptera.weatherforecast.data.provider.UnitProviderImpl
import com.helicoptera.weatherforecast.data.repository.ForecastRepository
import com.helicoptera.weatherforecast.data.repository.ForecastRepositoryImpl
import com.helicoptera.weatherforecast.ui.weather.current.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherStackApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider {CurrentWeatherViewModelFactory(instance(), instance() )}
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}