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
        var nextNode: Int = 0

        for(i in 0..this.nodes.size){
            var currentNodeEdges: ArrayList<Edge> = this.nodes[nextNode].getEdges()

            for(joinedEdge in 0..currentNodeEdges.size){
                var neighbourIndex: Int = currentNodeEdges.get(joinedEdge).getNeighbourIndex(nextNode)

                if(!this.nodes[neighbourIndex].isVisited()){
                    var tentative: Int = this.nodes[nextNode].getDistanceFromSource() + currentNodeEdges.get(joinedEdge).getLength()

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
        var storedDis = Int.MAX_VALUE

        for(i in 0..this.nodes.size){
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

        for(i in 0..this.nodes.size){
            output += ("\nThe shortest distance from node 0 to node " + i + " is " + nodes[i].getDistanceFromSource())
        }

        System.out.println(output)
    }

    fun getNodes(): ArrayList<Node> { return nodes }

    fun getEdges(): ArrayList<Edge> { return edges }

    fun getNoOfEdges(): Int { return noOfEdges }

}


