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

    @Query("SELECT * FROM LocationData WHERE id = :id")
    fun getLocationById(id: Int): Flow<LocationData>

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(location: LocationData): Long

    @Query("""
        SELECT LocationData.* 
        FROM LocationData
        LEFT JOIN Settings ON LocationData.id = Settings.currentLocationID
        WHERE Settings.id = 1
    """)
    fun getCurrentLocation(): LocationData



}