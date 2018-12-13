package com.example.jordan.sig_project.otherdijkstra

import android.util.Log
import com.example.jordan.sig_project.GeoArc
import com.example.jordan.sig_project.GeoPoint
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

}