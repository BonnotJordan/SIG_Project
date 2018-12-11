package com.example.jordan.sig_project

class Edge {
    private var fromNodeIndex: Int = 0
    private var toNodeIndex: Int = 0
    private var length : Int = 0

    constructor(fromNodeIndex: Int, toNodeIndex: Int, length: Int) {
        this.fromNodeIndex = fromNodeIndex
        this.toNodeIndex = toNodeIndex
        this.length = length
    }

    fun getFromNodeIndex(): Int { return fromNodeIndex }

    fun getToNodeIndex(): Int { return toNodeIndex }

    fun getLength(): Int { return length}

    fun getNeighbourIndex(nodeIndex: Int): Int{
        if(this.fromNodeIndex == nodeIndex){
            return this.toNodeIndex
        } else {
            return this.fromNodeIndex
        }
    }
}