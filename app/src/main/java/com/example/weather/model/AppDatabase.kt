package com.example.weather.model

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [LocationData::class, DayData::class, HourData::class, CurrentData::class, Settings::class],
    version = 8

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
}
