package com.example.jordan.sig_project.dijkstra

import com.example.jordan.sig_project.GeoPoint


class Edge(val id: String, val source: GeoPoint, val destination: GeoPoint, val weight: Float) {

    override fun toString(): String {
        return source.toString() + " " + destination
    }


}