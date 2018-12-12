package com.example.jordan.sig_project

import android.arch.persistence.room.*

@Dao
interface BusStopDao {

    @get:Query("SELECT * FROM GEO_POINT")
    val points: List<GeoPoint>

    @get:Query("SELECT * FROM GEO_ARC")
    val arcs: List<GeoArc>

}

@Entity(tableName = "GEO_POINT")
data class GeoPoint(
    @PrimaryKey @ColumnInfo(name = "GEO_POI_ID") var pointId: Int = 0,
    @ColumnInfo(name = "GEO_POI_LATITUDE") var latitude: Float = 0f,
    @ColumnInfo(name = "GEO_POI_LONGITUDE") var longitude: Float = 0f,
    @ColumnInfo(name = "GEO_POI_NOM") var name: String = "",
    @ColumnInfo(name = "GEO_POI_PARTITION") var partition: Int = 0
)

@Entity(tableName = "GEO_ARC")
data class GeoArc(
    @PrimaryKey @ColumnInfo(name = "GEO_ARC_ID") var arcId: Int = 0,
    @ColumnInfo(name = "GEO_ARC_DEB") var arcDeb: Int = 0,
    @ColumnInfo(name = "GEO_ARC_FIN") var arcFin: Int = 0,
    @ColumnInfo(name = "GEO_ARC_TEMPS") var arcTemps: Float = 0f,
    @ColumnInfo(name = "GEO_ARC_DISTANCE") var arcDistance: Float = 0f,
    @ColumnInfo(name = "GEO_ARC_SENS") var ardSens: Int = 0
)