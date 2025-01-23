package com.example.weather.data

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weather.domain.LocationFetchUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant

@Database(entities = [LocationData::class, DayData::class, HourData::class, CurrentData::class, Settings::class],
    version = 19

)
abstract class AppDatabase: RoomDatabase() {
    abstract fun locationDao(): LocationDAO
    abstract fun dayDao(): DayDAO
    abstract fun hourDao(): HourDAO
    abstract fun currentDataDao(): CurrentDataDao
    abstract fun settingsDao(): SettingsDAO


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "App_Database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class DatabaseCallback (private val context: Context): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Log.d("DatabaseCallback", "onCreate called")
            // Prepopulate the database
                 CoroutineScope(Dispatchers.IO).launch {
                     Log.d("DatabaseCallback", "Coroutine launched")
                    INSTANCE?.let { database ->

                        val location: Location? = LocationFetchUtil.fetchLocation(context)

                        Log.d("DatabaseCallback", "Location fetched: " + location.toString())

                        Log.d("DatabaseCallback", "Database instance obtained")
                        val latitude = location?.latitude ?: 0.0
                        val longitude = location?.longitude ?: 0.0


                        Log.d("DatabaseCallback", "Latitude: $latitude, Longitude: $longitude")
                        // Prepopulate the database with location settings
                        database.settingsDao().insertSettings(
                            Settings(
                                id = 1,
                                currentLocationID = 1,
                                // Add other default values if necessary
                            )
                        )
                        Log.d("DatabaseCallback", "Settings inserted")
                            database.locationDao().insert(
                                LocationData(
                                    name = "Home", // Setting it to home to avoid a reverse geolocation api call
                                    latitude = latitude,
                                    longitude = longitude,
                                    updatedAt = Instant.now().epochSecond.toInt(),
                                    isFavourite = true,
                                    id = 0,
                                )
                            )
                        Log.d("DatabaseCallback", "Location inserted")
                    }
                }
        }
    }
}
