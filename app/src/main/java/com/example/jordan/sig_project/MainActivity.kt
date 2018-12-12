package com.example.jordan.sig_project

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.huma.room_for_asset.RoomAsset
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = RoomAsset.databaseBuilder(
            this,
            AppDatabase::class.java,
            "lp_iem_sig.db"
        ).build()
        doAsync {
            var data = db.busStopDao().points
            var dataArc = db.busStopDao().arcs
            uiThread {
                Log.d("TAG_GET_DATA", data.toString())
                Log.d("TAG_GET_DATA", dataArc.toString())
            }
            //var result = data[1].busStopLatitude.toString()
        }
    }

    fun showMap(view: View){
        var intent: Intent = Intent(this@MainActivity,MapsActivity::class.java)
        startActivity(intent)
    }
}
