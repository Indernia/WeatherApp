package com.example.weather.Repository


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

// Base URL
private val BASE_URL = "https://api.openweathermap.org/geo/1.0/"


// Retrofit Builder
private val retrofit = Retrofit.Builder()
//    .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON
    .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

// Weather API Service Interface
interface GeoLocationAPIServices {
    @GET("direct")
    suspend fun getLocationsData(
        @Query("q") cityName: String,
        @Query("limit") limit: Int,
        @Query("appid") apiKey: String
    ): ResponseBody
}

// Singleton Object for API
object GeoLocationAPI {
    val retrofitService: GeoLocationAPIServices by lazy {
        retrofit.create(GeoLocationAPIServices::class.java)
    }
}

