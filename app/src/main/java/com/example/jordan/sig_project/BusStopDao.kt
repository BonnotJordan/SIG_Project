package com.example.jordan.sig_project

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface BusStopDao {
    @Query("SELECT * FROM BusStop")
    fun getAll(): List<BusStop>

    @Query("SELECT * FROM BusStop WHERE busStopId IN (:busStopIds)")
    fun loadAllByIds(busStopIds: IntArray):List<BusStop>

    @Insert
    fun insertAll(vararg busStop: BusStop)

    @Delete
    fun delete(busStop: BusStop)

}