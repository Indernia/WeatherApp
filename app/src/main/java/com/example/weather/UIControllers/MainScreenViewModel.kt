package com.example.weather.UIControllers

import androidx.lifecycle.ViewModel
import com.example.weather.model.Condition
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import com.example.weather.model.LocationData
import java.time.ZonedDateTime
class MainScreenViewModel: ViewModel() {
    lateinit var locationdata: LocationData

    fun makeTestLocationData(){
        locationdata = LocationData(
            name = "Test Location",
            updatedAt = ZonedDateTime.now(),
        )
        for (i in 1..7) {
            locationdata.days.add(
                DayData(
                    date = ZonedDateTime.now().plusDays(i.toLong()),
                    updatedAt = ZonedDateTime.now(),
                    dayOfWeek = when (i) {
                        1 -> "Monday"
                        2 -> "Tuesday"
                        3 -> "Wednesday"
                        4 -> "Thursday"
                        5 -> "Friday"
                        6 -> "Saturday"
                        7 -> "Sunday"
                        else -> "Invalid day" // This is optional if you know i will always be in range 1..7
                    },
                    maxTempC = 20.0 + (i % 2),
                    minTempC = 15.0 + (i % 2),

                    maxHumidity = 60.0 + (i % 2),
                    minHumidity = 40.0 + (i % 2),

                    maxUV = 3.0 + (i % 2),
                    minUV = 1.0 + (i % 2),

                    weatherCondition = Condition.CLEAR,
                )
            )
        }
        for (i in 1..24){
            locationdata.hours.add(
                HourData(
                    timestamp = ZonedDateTime.now().plusHours(i.toLong()),
                    temperature = 15.0 + i% 3,
                    humidity = 40.0 + i % 5,
                    windSpeed = 1.0 + i % 10,
                    updatedAt = ZonedDateTime.now(),
                    uv = 1.0 + i % 6,
                    condition = Condition.CLEAR,
                )
            )
        }
    }

}

