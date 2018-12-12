package com.example.jordan.sig_project

class Node {

    private var distanceFromSource: Int = Int.MAX_VALUE
    private var visited: Boolean = false
    private var edges: ArrayList<Edge> = ArrayList()

    fun getDistanceFromSource(): Int { return distanceFromSource }

    fun setDistanceFromSource(distanceFromSource: Int) {
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