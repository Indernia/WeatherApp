package com.example.weather.model


// Condition taken from the API we are using at:
// https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2

// More sub conditions can be found on the above website, but we will likely not use anything that precise for now
enum class Condition (conditionCode: Int) {
    THUNDERSTORM(200),
    DRIZZLE(300),
    RAIN(500),
    SNOW(600),
    ATMOSPHERE(700),
    CLEAR(800),
    CLOUDS(801)

}