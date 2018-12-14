package com.example.jordan.sig_project

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.jordan.sig_project.dijkstra.DijkstraAlgorithm
import com.example.jordan.sig_project.dijkstra.Graph
import com.huma.room_for_asset.RoomAsset
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.uiThread
import java.io.File
import java.nio.charset.Charset
import kotlin.collections.ArrayList

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

                var edgesBis: ArrayList<GeoArc> = ArrayList()

                edges.forEach{
                    var arc = GeoArc(0, it.arcFin, it.arcDeb, it.arcTemps, it.arcDistance, it.arcSens)
                    edgesBis.add(arc)
                }

                edges.addAll(edgesBis)


                var graph = Graph(nodes, edges)
                var dijkstraAlgorithm = DijkstraAlgorithm(graph)
                buttonDijkstra.onClick {
                    if (idDebut.text.isNotEmpty() && idFin.text.isNotEmpty()) {
                        var path = dijkstraAlgorithm.getResult(nodes.find { geoPoint -> geoPoint.pointId == idDebut.text.toString().toInt() }!!, nodes.find { geoPoint -> geoPoint.pointId == idFin.text.toString().toInt() }!!)
                        path?.let {
                            var kml = KMLFormatter().header
                            kml += KMLFormatter().getOutput(it)
                            kml += KMLFormatter().footer
                            File(filesDir.toString() + "/myfile.kml").writeText(kml, Charset.defaultCharset())
                            Toast.makeText(this@MainActivity, "KML généré", Toast.LENGTH_LONG).show()
                        }
                        if (path.isNullOrEmpty()) {
                            Toast.makeText(this@MainActivity, "Aucun chemin trouvé", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            //var result = data[1].busStopLatitude.toString()
        }
    }

    fun showMap(view: View){
        var intent: Intent = Intent(this@MainActivity,MapsActivity::class.java)
        startActivity(intent)
    }
}
