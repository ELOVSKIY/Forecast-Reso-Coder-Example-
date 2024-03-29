package com.helicoptera.weatherforecast.data

import com.helicoptera.weatherforecast.data.network.ConnectivityInterceptor
import com.helicoptera.weatherforecast.data.network.ConnectivityInterceptorImpl
import com.helicoptera.weatherforecast.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.weatherstack.com/current?access_key=d619cc04f396dc78bed5ddb69784d1e1&query=New%20York

const val API_KEY = "d619cc04f396dc78bed5ddb69784d1e1"

interface WeatherStackApiService {

    @GET("current")
    fun getCurrentWeather(
        @Query("query") location: String,
        @Query("units")units: String = "m"
    ): Deferred<CurrentWeatherResponse>

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor): WeatherStackApiService{
            val requestInterceptor = Interceptor {chain ->
                val url = chain.request().url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherStackApiService::class.java)

        }
    }
}