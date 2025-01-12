package com.example.weather.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DayDAO{
    @Query("SELECT * FROM DayData")
    fun getAll(): List<DayData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(days: List<DayData>)

}