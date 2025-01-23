package com.example.weather.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDAO {
    @Query("SELECT * FROM LocationData WHERE isDeleted = 0")
    fun getAll(): Flow<List<LocationData>>

    @Query("SELECT * FROM LocationData WHERE longitude = :lon AND latitude = :lat")
    fun getLocationByLatLon(lat: Double, lon: Double): Flow<LocationData?>

    @Query("SELECT * FROM LocationData WHERE id = :id")
    fun getLocationById(id: Long): Flow<LocationData>

    @Query("""
            UPDATE LocationData
            SET isFavourite = 1
            WHERE LocationData.id = :id
    """)
    fun markLocationAsFavouriteId(id: Long)

    @Query("""
            UPDATE LocationData
            SET isFavourite = 0
            WHERE LocationData.id = :id
    """)
    fun markLocationAsUnFavouriteId(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: LocationData): Long

    @Query("""
        SELECT LocationData.* 
        FROM LocationData
        LEFT JOIN Settings ON LocationData.id = Settings.currentLocationID
        WHERE Settings.id = 1
    """)
    fun getCurrentLocation(): LocationData

    @Query("""
        UPDATE LocationData
        SET isDeleted = 1
        WHERE id = :id
    """)
    fun setLocationAsDeleted(id: Long)

    @Query("""
        UPDATE LocationData
        SET isDeleted = 0
        WHERE id = :id
    """)
    fun setLocationAsNotDeleted(id: Long)


}