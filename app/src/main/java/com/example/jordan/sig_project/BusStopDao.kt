package com.example.jordan.sig_project

import android.arch.persistence.room.*

@Dao
interface BusStopDao {

    @get:Query("SELECT * FROM GEO_POINT")
    val points: List<GeoPoint>

}

@Entity(tableName = "GEO_POINT")
data class GeoPoint(
    @PrimaryKey @ColumnInfo(name = "GEO_POI_ID") var pointId: Int = 0,
    @ColumnInfo(name = "GEO_POI_LATITUDE") var latitude: Double = 0.toDouble(),
    @ColumnInfo(name = "GEO_POI_LONGITUDE") var longitude: Double = 0.toDouble(),
    @ColumnInfo(name = "GEO_POI_NOM") var name: String = "",
    @ColumnInfo(name = "GEO_POI_PARTITION") var partition: Int = 0
)