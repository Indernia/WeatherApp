package com.example.weather.domain

// Not serializable because for some reason retrofit and gradle are not able to serialize it for some dumb reason
data class CityGeoLocatorData(
    var name: String?,
    var lat: Double?,
    var lon: Double?,
    var country: String?,
    var state: String?,
)
