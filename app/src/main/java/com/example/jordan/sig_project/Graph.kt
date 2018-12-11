package com.example.jordan.sig_project

class Graph {
    private lateinit var nodes: ArrayList<Node>
    private var noOfNodes: Int = 0
    private var edges: ArrayList<Edge>
    private var noOfEdges: Int = 0


    constructor(edges: ArrayList<Edge>){
        this.edges = edges

        this.noOfNodes = calculateNoOfNodes(edges)

        for(i in 0..noOfNodes){
            this.nodes[i] = Node()
        }

        this.noOfEdges = edges.size

        for (edgeToAdd in 0..noOfEdges){
            this.nodes[edges[edgeToAdd].getFromNodeIndex()].getEdges().add(edges[edgeToAdd])
            this.nodes[edges[edgeToAdd].getToNodeIndex()].getEdges().add(edges[edgeToAdd])
        }

    }

    private fun calculateNoOfNodes(edges: ArrayList<Edge>): Int {
        var noOfNodes: Int = 0

        for(e in edges){
            if(e.getToNodeIndex() > noOfNodes)
                noOfNodes = e.getToNodeIndex()
            if(e.getFromNodeIndex() > noOfNodes)
                noOfNodes = e.getFromNodeIndex()
        }

        noOfNodes++

        return noOfNodes
    }

    fun calculateShortestDistances() {
        this.nodes[0].setDistanceFromSource(0)
        var nextNode: Int
    }

}


