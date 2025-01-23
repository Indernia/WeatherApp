package com.example.weather.domain

import android.content.Context
import android.util.Log
import com.example.weather.R
import com.example.weather.data.AppDatabase
import com.example.weather.data.CurrentData
import com.example.weather.data.DayData
import com.example.weather.data.HourData
import com.example.weather.data.LocationData
import com.example.weather.data.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
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

    // var currentCity : String = "" Commented out as the repo should never contain state

    suspend fun getCurrentCity(context: Context): String {
        val db = AppDatabase.getDatabase(context)
        var string: String = ""
        withContext(Dispatchers.IO){
           string = db.locationDao().getCurrentLocation().name
        }
        return string

    }

    fun getLocations(
        context: Context
    ): Flow<List<LocationData>> {
        val db = AppDatabase.getDatabase(context)
        return db.locationDao().getAll()
    }

    fun getCurrentLocation(context: Context): Flow<LocationData> {
        val db = AppDatabase.getDatabase(context)
        val settings: Flow<Settings> = db.settingsDao().getSettings()
        return settings.flatMapLatest { settings ->
            db.locationDao().getLocationById(settings.currentLocationID.toLong())
        }
    }

    suspend fun setCurrentLocation(LocationId: Long, context: Context){
        val db = AppDatabase.getDatabase(context)
        withContext(Dispatchers.IO) {
            db.settingsDao().updateCurrentLocation(id = LocationId)
        }
    }

    fun getCurrentDataLatestForCurrentLocation(context: Context): Flow<CurrentData> = flow {
        val db = AppDatabase.getDatabase(context)
        val currentData: Flow<CurrentData> = db.currentDataDao().getCurrentDataForCurrentLocation()
        emit(currentData.first())
    }

    suspend fun toggleLocationFavourite(
        context: Context,
        id: Long
    ){
        val db = AppDatabase.getDatabase(context)
        withContext(Dispatchers.IO) {
            val favourite = db.locationDao().getLocationById(id).firstOrNull()?.isFavourite ?: false

            if (!favourite) {
                db.locationDao().markLocationAsFavouriteId(id = id)
                Log.d("WeatherRepository", "Toggled location $id to favourite")
            } else {
                db.locationDao().markLocationAsUnFavouriteId(id = id)
                Log.d("WeatherRepository", "Toggled location $id to unfavourite")
            }
        }

    }

    fun deleteLocation(id: Long, context: Context) {
        val db = AppDatabase.getDatabase(context)
        db.locationDao().setLocationAsDeleted(id)
    }
    /**
     *
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
        Log.println(Log.DEBUG, "WeatherRepository", "Getting days")
        val db = AppDatabase.getDatabase(context)
        val dayDataList = db.dayDao().getSelectedCityDayData(currentTime = Instant.now().epochSecond.toInt(), limit).first()
        emit(dayDataList)

        var latestUpdate = 0

        withContext(Dispatchers.IO) {
            latestUpdate = db.dayDao().getLatestUpdateSelectedCity(Instant.now().epochSecond.toInt())
        }

        if (Instant.now().epochSecond - latestUpdate > dataStaleValue) {
            Log.println(Log.DEBUG, "WeatherRepository", "Updating data")
            var location: LocationData = LocationData(0, "", 0.0, 0.0, 0, false)
            withContext(Dispatchers.IO){
                Log.d("WeatherRepository", "Current location: test $location")
                location = db.locationDao().getCurrentLocation()
                Log.d("WeatherRepository", "Current location: all location ${db.locationDao().getAll().first().toString()}")

                Log.d("WeatherRepository", "Current location: location post new location $location")
            }

            // not always long as the weird generated DAO code will return null if nothing is available, check to prevent crash
            if (location == null) {
                return@flow
            }
            Log.d("WeatherRepository", "Current location: $location")
            UpdataData(lat = location.latitude, lon = location.longitude, name = location.name, context = context)
            val updatedDayDataList = db.dayDao().getSelectedCityDayData(currentTime = Instant.now().epochSecond.toInt(), limit).first()
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
        val hourDataList = db.hourDao().getSelectedCityHourData(currentTime = Instant.now().epochSecond.toInt(), limit).first()
        emit(hourDataList)
        Log.d("WeatherRepository", "Getting hours: $hourDataList")

        var latestUpdate = 0
        withContext(Dispatchers.IO) {
            latestUpdate = db.hourDao().getLatestUpdateSelectedCity()
            Log.d("WeatherRepository", "Latest update: $latestUpdate")
            Log.d("WeatherRepository", "having lat: $lat and lon: $lon")
        }

        if (Instant.now().epochSecond - latestUpdate > dataStaleValue) {

            var location: LocationData = LocationData(0, "", 0.0, 0.0, 0, false)
            withContext(Dispatchers.IO){
                // Doing this update as the UpdateData was made to take lat lon and not depend on the database to get the location
                location = db.locationDao().getCurrentLocation()
                Log.d("WeatherRepository", "Current location: $location")
            }


            if (location == null) {
                return@flow
            }
            UpdataData(lat = location.latitude, lon = location.longitude, name = location.name, context = context)
            val updatedHourDataList = db.hourDao().getSelectedCityHourData(currentTime = Instant.now().epochSecond.toInt(), limit).first()
            emit(updatedHourDataList)
        }

    }

    /**
     * @param context Application context
     * @param lat Latitude of location
     * @param lon Longitude of location
     *
     * Sends a flow with the current weather data and makes sure to update it if the data is stale and sends it back
     */
    fun getCurrent(
        context: Context,
        lat: Double,
        lon: Double,
        name: String
    ): Flow<CurrentData> = flow {
        val db = AppDatabase.getDatabase(context)

        // Get the current data from the database
        val currentData = db.currentDataDao().getCurrentDataForCurrentLocation().first()
        emit(currentData)
        Log.d("WeatherRepository", "Getting current data: $currentData")

        var latestUpdate = 0
        withContext(Dispatchers.IO) {
            latestUpdate = db.currentDataDao().getLatestUpdateSelectedCity(
            )
            Log.d("WeatherRepository", "Latest current data update: $latestUpdate")
        }

        // Check if the current data is stale
        if (Instant.now().epochSecond - latestUpdate > dataStaleValue) {
            Log.println(Log.DEBUG, "WeatherRepository", "Updating current data")

            var location: LocationData = LocationData(0, "", 0.0, 0.0, 0, false)
            withContext(Dispatchers.IO){
                location = db.locationDao().getCurrentLocation()
                Log.d("WeatherRepository", "Current location: $location")
            }

            if (location == null) {
                return@flow
            }

            // Call the UpdataData function to update the data
            UpdataData(lat = location.latitude, lon = location.longitude, name = location.name, context = context)

            // Fetch the updated current data
            val updatedCurrentData = db.currentDataDao().getCurrentDataForCurrentLocation().first()
            emit(updatedCurrentData)
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
            Log.d("WeatherRepository", "Response: $response")
            Log.d("WeatherRepository", "having called the api at: ${Instant.now().epochSecond.toString()}")
        } catch (e: Exception) {
            e.printStackTrace()
            response = ResponseBody.create(null, "")
            Log.d("WeatherRepository", "Exception: $e")
        }
        if (response.contentLength() == 0L) {
            Log.d("WeatherRepository", "Response is empty")
            return
        }

        // Note that the response.string is destructive meaning it can only be called once
        val responseString = response.string()
        val responseJson = responseString
        val jsonObj = Json.parseToJsonElement(responseJson).jsonObject

        var locationId: Long = 0
        withContext(Dispatchers.IO) {
            val location: Flow<LocationData?> = db.locationDao().getLocationByLatLon(lat, lon)

            if (location.first() == null) {
                val locationData = LocationData(
                    name = name,
                    latitude = lat,
                    longitude = lon,
                    updatedAt = currentTimestamp.toInt(DurationUnit.SECONDS), // As the response from openweathermap is given in seconds
                    id = 0, // placeholder as it is auto generated
                )
                locationId = db.locationDao().insert(locationData)
                Log.d("WeatherRepository", "Location inserted: $locationData")
            } else {
                val locationTempData = location.first()
                locationId = locationTempData?.id ?: 0
                db.locationDao().setLocationAsNotDeleted(locationId)
                Log.d("WeatherRepository", "Location already exists: $locationTempData")

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

        withContext(Dispatchers.IO) {
            db.hourDao().insertAll(hourDataList)
        }

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

        withContext(Dispatchers.IO) {
            if (dayDataList != null) {
                db.dayDao().insertAll(dayDataList)
            }
        }


        val currentData = jsonObj["current"]?.jsonObject
        val currentDataObject: CurrentData = CurrentData(
            timestamp = currentData?.get("dt")?.jsonPrimitive?.content?.toInt() ?: 0,
            locationID = locationId,
            updatedAt = currentTimestamp.toInt(DurationUnit.SECONDS),
            temperature = currentData?.get("temp")?.jsonPrimitive?.content?.toDouble() ?: 0.0,
            feelsLike = currentData?.get("feels_like")?.jsonPrimitive?.content?.toDouble() ?: 0.0,
            humidity = currentData?.get("humidity")?.jsonPrimitive?.content?.toDouble() ?: 0.0,
            uvi = currentData?.get("uvi")?.jsonPrimitive?.content?.toDouble() ?: 0.0,
            windSpeed = currentData?.get("wind_speed")?.jsonPrimitive?.content?.toDouble() ?: 0.0,
            condition = currentData?.get("weather")?.jsonArray?.get(0)?.jsonObject?.get("main")?.jsonPrimitive?.content ?: "condition not found"
        )


        withContext(Dispatchers.IO) {
            db.currentDataDao()
        }
    }
}
