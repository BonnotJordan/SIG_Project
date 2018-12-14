package com.example.jordan.sig_project.dijkstra

import android.util.Log
import com.example.jordan.sig_project.GeoArc
import com.example.jordan.sig_project.GeoPoint
import java.lang.Math.*
import java.util.*

class DijkstraAlgorithm(graph: Graph) {

    private val listPoint: List<GeoPoint> = graph.listPoint
    private val listArc: List<GeoArc> = graph.listArc
    var settledNodes: Set<GeoPoint> = HashSet()
    var unSettledNodes: Set<GeoPoint> = HashSet()
    var predecessors: Map<GeoPoint, GeoPoint> = HashMap()
    var distance: Map<GeoPoint, Float> = HashMap()

    fun reset(){
        settledNodes = HashSet()
        unSettledNodes = HashSet()
        predecessors = HashMap()
        distance = HashMap()
    }

    fun getResult(source: GeoPoint, target: GeoPoint): LinkedList<GeoPoint>? {
        execute(source)
        val path = LinkedList<GeoPoint>()
        var etape = target
        if (predecessors[etape] == null) {
            return null
        }
        path.add(etape)
        while (predecessors[etape] != null) {
            etape = predecessors[etape]!!
            path.add(etape)
        }
        path.reverse()
        return path
    }

    fun execute(source: GeoPoint) {
        distance += source to 0.0f
        unSettledNodes += source
        while (unSettledNodes.size > 0) {
            val node = getMinimum(unSettledNodes)
            node?.let {
                settledNodes += node
                unSettledNodes -= node
                findMinimalDistances(node)
            }
        }
    }

    private fun findMinimalDistances(node: GeoPoint) {
        val adjacentNodes = getNeighbors(node)
        for (target in adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance += target to (getShortestDistance(node) + getDistance(node, target))
                predecessors += target to node
                unSettledNodes += target
            }
        }

    }

    private fun getDistance(node: GeoPoint, target: GeoPoint): Float {
        for (edge in listArc) {
            if (edge.arcDeb == node.pointId && edge.arcFin == target.pointId) {
                return edge.arcTemps
            }
        }
        throw RuntimeException("Should not happen")
    }

    private fun getNeighbors(node: GeoPoint): List<GeoPoint> {
        val neighbors = ArrayList<GeoPoint>()
        for (edge in listArc) {
            val point = listPoint.find { it.pointId == edge.arcFin }
            if ( node.pointId == edge.arcDeb && !isSettled(point)) {
                //Log.d("TESTTPOINT", point.toString())
                if(point != null) neighbors.add(point)
            }
        }
        return neighbors
    }

    private fun getMinimum(vertexes: Set<GeoPoint>): GeoPoint? {
        var minimum: GeoPoint? = null
        for (vertex in vertexes) {
            if (minimum == null) {
                minimum = vertex
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex
                }
            }
        }
        return minimum
    }

    private fun isSettled(vertex: GeoPoint?): Boolean {
        return settledNodes.contains(vertex)
    }

    private fun getShortestDistance(destination: GeoPoint?): Float {
        val d = distance[destination]
        return d ?: Float.MAX_VALUE
    }

    private fun convertDegToLamb(lat: Double, lon: Double): List<Double> {
        val lamb: ArrayList<Double> = ArrayList()
        val n = 0.7289686274
        val C = 11745793.39
        val e = 0.08248325676
        val Xs = 600000.0
        val Ys = 8199695.768

        var GAMMA0 = (3600 * 2).toDouble() + (60 * 20).toDouble() + 14.025
        GAMMA0 = GAMMA0 / (180 * 3600) * Math.PI
        val lati = lat / 180 * Math.PI
        val longi = lon / 180 * Math.PI
        val L = 0.5 * log((1 + sin(lati)) / (1 - sin(lati))) - e / 2 * log((1 + e * sin(lati)) / (1 - e * sin(lati)))
        val R = C * Math.exp(-n * L)
        val GAMMA = n * (longi - GAMMA0)

        val Lx = Xs + R * sin(GAMMA)
        val Ly = Ys - R * cos(GAMMA)

        lamb.add(Lx)
        lamb.add(Ly)

        return lamb
    }

    fun distancePoint(xa: Double, ya: Double, xb: Double, yb: Double): Double {

        return Math.sqrt(Math.pow(xb - xa, 2.0) + Math.pow(yb - ya, 2.0))
    }

}