package com.example.weather.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface DayDAO{
    @Query("SELECT * FROM DayData")
    fun getAll(): Flow<List<DayData>>

    @Query("""
        SELECT DayData.*
        FROM DayData
        LEFT JOIN LOCATIONDATA ON DayData.location = LocationData.id
        WHERE LocationData.latitude = :lat AND LocationData.longitude = :lon AND DayData.date > :currentTime
        ORDER BY DayData.date ASC
        LIMIT :limit
    """)
    fun getFromLatLon(lat: Double, lon: Double, currentTime: Int, limit:Int = 7): Flow<List<DayData>>

    @Query("""
        SELECT DayData.updatedAt
        FROM DayData
        LEFT JOIN LocationData ON DayData.location = LocationData.id
        WHERE LocationData.latitude = :lat AND LocationData.longitude = :lon
        ORDER BY DayData.updatedAt DESC
        LIMIT 1
    """)
    fun getLatestUpdate(lat: Double, lon: Double): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(days: List<DayData>)

}