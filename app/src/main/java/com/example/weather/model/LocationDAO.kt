package com.example.weather.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDAO {
    @Query("SELECT * FROM LocationData")
    fun getAll(): Flow<List<LocationData>>

    @Query("SELECT * FROM LocationData WHERE longitude = :lon AND latitude = :lat")
    fun getLocationByLatLon(lat: Double, lon: Double): Flow<LocationData?>

    @Query("""
            UPDATE LocationData
            SET isFavourite = 1
            WHERE latitude = :lat AND longitude = :lon
    """)
    fun markLocationAsFavouriteLatLon(lat: Double, lon: Double)

    @Query("""
            UPDATE LocationData
            SET isFavourite = 0
            WHERE latitude = :lat AND longitude = :lon
    """)
    fun markLocationAsUnFavouriteLatLon(lat: Double, lon: Double)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(location: LocationData): Long



}