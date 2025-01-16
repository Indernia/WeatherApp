package com.example.weather.model

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

    @Insert
    fun insertCurrentData(currentData: CurrentData)
}