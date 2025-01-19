package com.example.weather.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// Note no insert statements have been declared as we do not want multiple settings, unless sometimes in the future we make multiple profiles
@Dao
interface SettingsDAO {

    @Query("""
        SELECT * 
        FROM Settings
        WHERE Settings.id = 1 -- This is to make sure that we always get the initial settings and there should never be more, so if multiple rows are made they will be ignored
        """)
    fun getSettings(): Flow<Settings>

    @Update
    fun updateSettings(settings: Settings)

    @Query("""
        UPDATE Settings
        SET currentLocationID = :id
        WHERE Settings.id = 1
        """)
    fun updateCurrentLocation(id: Long)


    // Note this should only be used on startup as we handle all settings in id = 1
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertSettings(settings: Settings)

}