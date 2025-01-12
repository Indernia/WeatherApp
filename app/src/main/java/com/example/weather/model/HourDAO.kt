package com.example.weather.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface HourDAO {
    @Query("SELECT * FROM HourData")
    fun getAll(): List<HourData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hours: List<HourData>)



}