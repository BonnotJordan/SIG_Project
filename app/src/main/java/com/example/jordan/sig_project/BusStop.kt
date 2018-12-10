package com.example.jordan.sig_project

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class BusStop (
    @PrimaryKey var busStopId: Int,
    @ColumnInfo(name = "GEO_POI_LATITUDE") var busStopLatitude: Double?,
    @ColumnInfo(name = "GEO_POI_LONGITUDE") var busStopLongitude: Double?
)