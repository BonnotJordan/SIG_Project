package com.example.jordan.sig_project

class Graph(private var edges: ArrayList<Edge>) {

    private var nodes: ArrayList<Node> = ArrayList()
    private var noOfNodes: Int = 0
    private var noOfEdges: Int = 0


    init {
        this.noOfNodes = calculateNoOfNodes(edges)
        for(i in 0 until noOfNodes){
            nodes.add(Node())
        }

        this.noOfEdges = edges.size
        for (edgeToAdd in 0 until noOfEdges){
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
        this.nodes[0].setDistanceFromSource(0f)
        var nextNode: Int = 0

        for(node in nodes){
            var currentNodeEdges: ArrayList<Edge> = this.nodes[nextNode].getEdges()

            for(joinedEdge in currentNodeEdges){
                var neighbourIndex: Int = joinedEdge.getNeighbourIndex(nextNode)

                if(!this.nodes[neighbourIndex].isVisited()){
                    var tentative: Float = this.nodes[nextNode].getDistanceFromSource() + joinedEdge.getLength()

                    if(tentative < nodes[neighbourIndex].getDistanceFromSource())
                        nodes[neighbourIndex].setDistanceFromSource(tentative)
                }
            }

            nodes[nextNode].setVisited(true)

            nextNode = getNodeShortestDistance()
        }
    }

    private fun getNodeShortestDistance(): Int {
        var storedNodeIndex: Int = 0
        var storedDis = Float.MAX_VALUE

        for(i in 0 until this.nodes.size){
            var currentDist = this.nodes[i].getDistanceFromSource()

            if(!this.nodes[i].isVisited() && currentDist < storedDis){
                storedDis = currentDist
                storedNodeIndex = i
            }
        }

        return storedNodeIndex
    }

    fun printResult() {
        var output: String = "Number of nodes = " + this.noOfNodes
        output += "\nNumber of edges = " + this.noOfEdges

        for(i in 0 until this.nodes.size){
            output += ("\nThe shortest distance from node 0 to node " + i + " is " + nodes[i].getDistanceFromSource())
        }

        System.out.println(output)
    }

}


