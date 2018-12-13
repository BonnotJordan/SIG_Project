package com.example.jordan.sig_project

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jordan.sig_project.otherdijkstra.DijkstraAlgorithm
import com.example.jordan.sig_project.otherdijkstra.Edge
import com.example.jordan.sig_project.otherdijkstra.Graph
import com.example.jordan.sig_project.otherdijkstra.Vertex
import com.huma.room_for_asset.RoomAsset
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.nio.charset.Charset
import java.util.*

class MainActivity : AppCompatActivity() {

    var nodes = ArrayList<GeoPoint>()
    var edges = ArrayList<GeoArc>()

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
                //Log.d("TAG_GET_DATA", data.toString())
                //Log.d("TAG_GET_DATA", dataArc.toString())
                //var edges: ArrayList<Edge> = ArrayList()
                //for (arc in dataArc) {
                    //edges.add(Edge(arc.arcDeb, arc.arcFin, arc.arcDistance))
                //}
                //var graph: Graph = Graph(edges)
                //graph.calculateShortestDistances()
                //graph.printResult()
                nodes.addAll(data)
                edges.addAll(dataArc)


                var graph = Graph(nodes, edges)
                var dijkstraAlgorithm = DijkstraAlgorithm(graph)
                var path = dijkstraAlgorithm.getResult(nodes.find { geoPoint -> geoPoint.pointId == 1 }!!, nodes.find { geoPoint -> geoPoint.pointId == 81 }!!)
                var kml = KMLFormatter().header
                kml += KMLFormatter().getOutput(path!!)
                kml += KMLFormatter().footer
                Log.d("KML", kml)

                File(filesDir.toString() + "/myfile.kml").writeText(kml, Charset.defaultCharset())

                Log.d("kml", "finish")
            }
            //var result = data[1].busStopLatitude.toString()
        }
    }

}
