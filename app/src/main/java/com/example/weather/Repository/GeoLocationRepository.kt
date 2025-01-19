package com.example.weather.Repository

import android.util.Log
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.ResponseBody

class GeoLocationRepository {

    fun convertResponseBodyToGeoLocationData(responseBody: ResponseBody): List<CityGeoLocatorData> {
        val response: ResponseBody = responseBody
        val responseString:String = response.string()
        if (responseString.isEmpty()) {
            return emptyList()
        }
        Log.d("GeoLocationRepository", "Response String: $responseString")
        val jsonObj: JsonArray = Json.parseToJsonElement(responseString).jsonArray

        val cityList: List<CityGeoLocatorData> = jsonObj.jsonArray.map { cityElement ->
            Log.d("GeoLocationRepository", "City Element: $cityElement")
            CityGeoLocatorData(
                name = cityElement.jsonObject["name"]?.jsonPrimitive?.content ?: "",
                lat = cityElement.jsonObject["lat"]?.jsonPrimitive?.content?.toDouble(),
                lon = cityElement.jsonObject["lon"]?.jsonPrimitive?.content?.toDouble(),
                state = cityElement.jsonObject["state"]?.jsonPrimitive?.content ?: "",
                country = cityElement.jsonObject["country"]?.jsonPrimitive?.content ?: "",
            )
        }
        return cityList
    }
}