package com.example.jordan.sig_project

class Edge(private var fromNodeIndex: Int, private var toNodeIndex: Int, private var length: Float) {

    fun getFromNodeIndex(): Int { return fromNodeIndex }

    fun getToNodeIndex(): Int { return toNodeIndex }

    fun getLength(): Float { return length}

    fun getNeighbourIndex(nodeIndex: Int): Int{
        return if(this.fromNodeIndex == nodeIndex){
            this.toNodeIndex
        } else {
            this.fromNodeIndex
        }
    }
}