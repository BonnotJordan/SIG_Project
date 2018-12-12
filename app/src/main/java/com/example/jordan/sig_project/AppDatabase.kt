package com.example.jordan.sig_project

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = [GeoPoint::class, GeoArc::class],version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun busStopDao(): BusStopDao
}