package com.example.jordan.sig_project

class Graph {
    private lateinit var nodes: Array<Node>
    private var noOfNodes: Int = 0
    private lateinit var edges: Array<Edge>
    private var noOfEdges: Int = 0


    constructor(edges: Array<Edge>){
        this.edges = edges

        this.noOfNodes = calculateNoOfNodes(edges)
        


    }

}


