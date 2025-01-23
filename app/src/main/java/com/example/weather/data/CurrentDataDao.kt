package com.example.weather.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentDataDao {
    @Query("""
        SELECT * 
        FROM CurrentData
        WHERE CurrentData.locationID = :locationId
        ORDER BY CurrentData.timestamp DESC
        LIMIT 1
    """)
    fun getCurrentDataFromLocation(locationId: Long): Flow<CurrentData>

    @Query("""
        SELECT CurrentData.* 
        FROM CurrentData
        LEFT JOIN Settings ON CurrentData.locationID = Settings.currentLocationID
        WHERE Settings.id = 1
        ORDER BY CurrentData.timestamp DESC
        LIMIT 1
    """)
    fun getCurrentDataForCurrentLocation(): Flow<CurrentData>

    @Insert
    fun insertCurrentData(currentData: CurrentData)

    @Query("""
        SELECT CurrentData.updatedAt
        FROM CurrentData
        LEFT JOIN Settings ON CurrentData.locationID = Settings.currentLocationID
        WHERE Settings.id = 1
        ORDER BY CurrentData.updatedAt DESC
        LIMIT 1
    """)
    fun getLatestUpdateSelectedCity() : Int
}