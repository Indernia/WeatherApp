package com.example.weather.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDAO {
    @Query("SELECT * FROM LocationData")
    fun getAll(): List<LocationData>

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend fun insertAll(locations: List<LocationData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: LocationData): Long


}