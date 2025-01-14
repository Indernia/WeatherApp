package com.example.weather.Repository

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.weather.R
import com.example.weather.model.AppDatabase
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import com.example.weather.model.LocationDAO
import com.example.weather.model.LocationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.ResponseBody
import java.time.Instant
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

class WeatherRepository {

    // We should make some study or something to check what interval would be good here, it does not make sense to wait indefinitely, but it also does not make sense to update it every frame
    private val dataStaleValue: Int = 60 * 10 // Keep formatting as seconds in minute * minutes so default 60 seconds in a minute * 10 minutes

    fun getLocations(
        context: Context
    ): Flow<List<LocationData>> {
        val db = AppDatabase.getDatabase(context)
        return db.locationDao().getAll()
    }

    /**
     * @param context Application context
     * @param lat Latitude of location
     * @param lon longitude of location
     * @param limit the number of hours to return
     *
     * Sends a flow with the day data values and makes sure to update it if the data is stale and sends it back
     */
    fun getDays(
        context: Context,
        lat: Double,
        lon: Double,
        name: String,
        limit: Int = 7
    ): Flow<List<DayData>> = flow {
        val db = AppDatabase.getDatabase(context)
        val dayDataList = db.dayDao().getFromLatLon(lat, lon, limit).first()
        emit(dayDataList)

        var latestUpdate = 0

        withContext(Dispatchers.IO) {
            latestUpdate = db.dayDao().getLatestUpdate(lat, lon)
        }

        if (Instant.now().epochSecond - latestUpdate > dataStaleValue) {
            UpdataData(lat = lat, lon = lon, name = name, context = context)
            val updatedDayDataList = db.dayDao().getFromLatLon(lat, lon, limit).first()
            emit(updatedDayDataList)
        }

    }

    /**
     * @param context Application context
     * @param lat Latitude of location
     * @param lon longitude of location
     * @param limit the number of hours to return
     *
     * Sends a flow with the current hour values and makes sure to update it if the data is stale and sends it back
     */
    fun getHours(
        context: Context,
        lat: Double,
        lon: Double,
        name: String,
        limit: Int = 24
    ): Flow<List<HourData>> = flow {
        val db = AppDatabase.getDatabase(context)
        val hourDataList = db.hourDao().getAllFromLatLon(lat, lon, limit).first()
        emit(hourDataList)

        val latestUpdate = db.hourDao().getLatestUpdate(lat, lon)

        if (Instant.now().epochSecond - latestUpdate > dataStaleValue) {
            UpdataData(lat = lat, lon = lon, name = name, context = context)
            val updatedHourDataList = db.hourDao().getAllFromLatLon(lat, lon, limit).first()
            emit(updatedHourDataList)
        }

    }




    suspend fun UpdataData(
        name: String = "Copenhagen",
        lat: Double = 55.67594,
        lon: Double = 12.56553,
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
                latitude = lat,
                longitude = lon,
                exclude = "minutely",
                apiKey = context.getString(R.string.api_key)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            response = ResponseBody.create(null, "")
        }

        // Note that the response.string is destructive meaning it can only be called once
        val responseString = response.string()
        val responseJson = responseString
        val jsonObj = Json.parseToJsonElement(responseJson).jsonObject

        var locationId: Long = 0
        withContext(Dispatchers.IO){
            val location: Flow<LocationData?> = db.locationDao().getLocationByLatLon(lat, lon)

            if(location.first() == null){
                val locationData = LocationData(
                    name = name,
                    latitude = lat,
                    longitude = lon,
                    updatedAt = currentTimestamp.toInt(DurationUnit.SECONDS), // As the response from openweathermap is given in seconds
                    id = 0, // placeholder as it is auto generated
                )
                locationId = db.locationDao().insert(locationData)
            } else{
                val locationTempData = location.first()
                locationId = locationTempData?.id ?: 0
            }
        }



        val hourData: JsonArray? = jsonObj["hourly"]?.jsonArray

        val hourDataList: List<HourData> = hourData?.map { hourlyElement ->
            val hourlyObject = hourlyElement.jsonObject
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
            val dailyObject = dailyElement.jsonObject
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

        withContext(Dispatchers.IO){
            if (dayDataList != null) {
                db.dayDao().insertAll(dayDataList)
            }
        }



        Log.println(Log.DEBUG, "WeatherData", "" + hourDataList.get(0).toString())


    }

}