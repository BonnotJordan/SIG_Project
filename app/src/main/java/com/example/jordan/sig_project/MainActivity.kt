package com.example.jordan.sig_project

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            "lp_iem_sig.sqlite"
        ).build()
        doAsync {
            Log.d("TAG_GET", "before")
            var data = db.busStopDao().points
            Log.d("TAG_GET", "first")
            uiThread {
                Log.d("TAG_GET", "test")
                Log.d("TAG_GET_DATA", data.toString())
            }
            //var result = data[1].busStopLatitude.toString()
        }
    }
}
