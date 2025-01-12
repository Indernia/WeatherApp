package com.example.weather.Repository

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.weather.model.AppDatabase
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import com.example.weather.model.LocationDAO
import com.example.weather.model.LocationData
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.ResponseBody
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

class WeatherRepository {


    fun getLocations(
        context: Context
    ): List<LocationData> {
        val db = AppDatabase.getDatabase(context)
        return db.locationDao().getAll()
    }


    fun getDays(
        context: Context
    ): List<DayData>{
        val db = AppDatabase.getDatabase(context)
        return db.dayDao().getAll()
    }

    fun getHours(
        context: Context
    ): List<HourData>{
        val db = AppDatabase.getDatabase(context)
        return db.hourDao().getAll()
    }


    suspend fun UpdataData(
        name: String,
        context: Context
    ) {

        // get database instance from singleton
        val db by lazy { AppDatabase.getDatabase(context) }

        // both store to be removed when city handling is made
        val latituteStore = 33.44
        val longitudeStore = -94.04

        //Grab time for updated at reference
        val currentTimestamp = System.currentTimeMillis().seconds
        //load response
        var response: ResponseBody
        try {
            response = WeatherApi.retrofitService.getWeatherData(
                latitude = latituteStore,
                longitude = longitudeStore,
                exclude = "minutely",
                apiKey = "f7926ce66cde68fb4ad96bd11dc8f468"
            )
        } catch (e: Exception) {
            e.printStackTrace()
            response = ResponseBody.create(null, "")
        }

        // Note that the response.string is destructive meaning it can only be called once
        val responseString = response.string()
        val responseJson = responseString
        val jsonObj = Json.parseToJsonElement(responseJson).jsonObject

        val locationData = LocationData(
            name = name,
            latitude = latituteStore,
            longitude = longitudeStore,
            updatedAt = currentTimestamp.toInt(DurationUnit.SECONDS), // As the response from openweathermap is given in seconds
            id = 0, // placeholder as it is auto generated
        )

        val locationId = db.locationDao().insert(locationData)

        val hourData: JsonArray? = jsonObj["hourly"]?.jsonArray

        val hourDataList: List<HourData> = hourData?.map { hourlyElement ->
            var hourlyObject = hourlyElement.jsonObject
            HourData(
                timestamp = hourlyObject["dt"]?.jsonPrimitive?.content?.toInt() ?: 0,
                location = locationId,
                updatedAt = currentTimestamp.toInt(DurationUnit.SECONDS),

                humidity = hourlyObject["humidity"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                temperature = hourlyObject["temp"]?.jsonPrimitive?.content?.toDouble()
                    ?: 0.0, // currently in kelvin
                windSpeed = hourlyObject["wind_speed"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                uv = hourlyObject["uvi"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                condition = hourlyObject["weather"]?.jsonArray?.get(0)?.jsonObject?.get("main")?.jsonPrimitive?.content
                    ?: "",
            )
        } ?: emptyList()

        db.hourDao().insertAll(hourDataList)

        val dayData: JsonArray? = jsonObj["daily"]?.jsonArray

        val dayDataList: List<DayData>? = dayData?.map { dailyElement ->
            var dailyObject = dailyElement.jsonObject
            DayData(
                date = dailyObject["dt"]?.jsonPrimitive?.content ?: "",
                location = locationId,
                updatedAt = currentTimestamp.toInt(DurationUnit.SECONDS),
                maxTempK = dailyObject["temp"]?.jsonObject?.get("max")?.jsonPrimitive?.content?.toDouble(),
                minTempK = dailyObject["temp"]?.jsonObject?.get("min")?.jsonPrimitive?.content?.toDouble(),
                tempK = dailyObject["temp"]?.jsonObject?.get("day")?.jsonPrimitive?.content?.toDouble(),
                humidity = dailyObject["humidity"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                windSpeed = dailyObject["wind_speed"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                windGustSpeed = dailyObject["wind_gust"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                uvi = dailyObject["uvi"]?.jsonPrimitive?.content?.toDouble() ?: 0.0,
                weatherCondition = dailyObject["weather"]?.jsonArray?.get(0)?.jsonObject?.get("main")?.jsonPrimitive?.content,
            )
        }



        Log.println(Log.DEBUG, "WeatherData", "" + hourDataList.get(0).toString())


    }

}