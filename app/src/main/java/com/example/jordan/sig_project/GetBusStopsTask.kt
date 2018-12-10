package com.example.jordan.sig_project

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.huma.room_for_asset.RoomAsset

class GetBusStopsTask() : AsyncTask<Void, Void, String>() {

    lateinit var mContext: Context
    constructor(context: Context) : this() {
        mContext = context
    }

    override fun doInBackground(vararg p0: Void?): String {
        val db = RoomAsset.databaseBuilder(
            mContext,
            AppDatabase::class.java,
            "lp_iem_sig.db"
        ).build()

        var data = db.busStopDao().getAll()
        Log.d("TAG_GET_DATA",data.toString())

        //var result = data[1].busStopLatitude.toString()
        return "hello"
    }

    override fun onPostExecute(result: String?) {
        Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show()
    }



}