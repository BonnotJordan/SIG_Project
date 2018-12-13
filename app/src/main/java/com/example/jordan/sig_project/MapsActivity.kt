package com.example.jordan.sig_project

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.huma.room_for_asset.RoomAsset
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var data: List<GeoPoint>
    lateinit var dataArc: List<GeoArc>
    lateinit var dataPointDebut: List<GeoPoint>
    lateinit var datapointFin: List<GeoPoint>
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val db = RoomAsset.databaseBuilder(
            this,
            AppDatabase::class.java,
            "lp_iem_sig.db"
        ).build()
        doAsync {
            data = db.busStopDao().points
            dataArc = db.busStopDao().arcs
            dataPointDebut = db.busStopDao().pointArcDeb
            datapointFin = db.busStopDao().pointArcFin
            uiThread {
                Log.d("TAG_GET_DATA", data.toString())
                Log.d("TAG_GET_DATA", dataArc.toString())
            }
            //var result = data[1].busStopLatitude.toString()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        for (i in 0..((data.size) - 1)) {
            val busStop = LatLng(data[i].latitude.toDouble(), data[i].longitude.toDouble())
            var marker: Marker =  mMap.addMarker(MarkerOptions().position(busStop).title("Sélectionner l'arrêt : " + data[i].name))
        }


        for (j in 0..((dataPointDebut.size) - 1)) {
            var pointDeb = LatLng(dataPointDebut[j].latitude.toDouble(), dataPointDebut[j].longitude.toDouble())
            var pointFin = LatLng(datapointFin[j].latitude.toDouble(), datapointFin[j].longitude.toDouble())
            var line: PolylineOptions = PolylineOptions().add(pointDeb, pointFin)
            if (dataPointDebut[j].partition == 1) {
                line.color(Color.BLUE)
            }
            if (dataPointDebut[j].partition == 2) {
                line.color(Color.RED)
            }
            if (dataPointDebut[j].partition == 3) {
                line.color(Color.YELLOW)
            }
            if (dataPointDebut[j].partition == 4) {
                line.color(Color.GREEN)
            }
            if (dataPointDebut[j].partition == 5) {
                line.color(Color.CYAN)
            }
            if (dataPointDebut[j].partition == 6) {
                line.color(Color.MAGENTA)
            }
            if (dataPointDebut[j].partition == 7) {
                line.color(Color.GRAY)
            }
            if (dataPointDebut[j].partition == 21) {
                line.color(Color.BLACK)
            }



            mMap.addPolyline(line)
        }

        mMap.setOnInfoWindowClickListener {
            var string = ""
            if(it.title.startsWith("Sélectionner",true)){

                it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                it.title = ("Déselectionner l'arrêt")
            } else {
                it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                it.title = ("Sélectionner l'arrêt: " + string)
            }
        }




    }



}