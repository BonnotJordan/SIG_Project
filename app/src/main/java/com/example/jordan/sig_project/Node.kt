package com.example.jordan.sig_project

class Node {

    private var distanceFromSource: Float = Float.MAX_VALUE
    private var visited: Boolean = false
    private var edges: ArrayList<Edge> = ArrayList()

    fun getDistanceFromSource(): Float { return distanceFromSource }

    fun setDistanceFromSource(distanceFromSource: Float) {
        this.distanceFromSource = distanceFromSource
    }

    fun isVisited(): Boolean { return visited }

    fun setVisited(visited: Boolean) {
        this.visited = visited
    }

    fun getEdges(): ArrayList<Edge> { return edges }

    fun setEdges(edges: ArrayList<Edge>) {
        this.edges = edges
    }


}