package com.tijmen.entities;

import java.util.HashSet;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;

public class GraphHandler {
    /**
     * The average grade of the graph is the average grade of all vertices in the graph.
     * The grade of a vertex is the amount of edges leaving the vertex.
     * @param graph The graph
     * @return The average grade of the graph
     */
    public static OptionalDouble averageGrade(Graph graph) {
        return graph.
                getAdjacencyLists().
                values().
                stream().
                mapToDouble(vertices -> vertices.size()).
                average();
    }

    /**
     *
     * @param graph the graph
     * @param grade the requested grade
     * @return all vertices that have grade of requested grade
     */
    public static Set<Vertex> VerticesOfGrade(Graph graph, int grade) {
        Set<Vertex> edgesOfGrade = new HashSet<>();
        for(Map.Entry<Vertex, Set<Vertex>> adjacencyList : graph.getAdjacencyLists().entrySet()) {
            if(adjacencyList.getValue().size() == grade) {
                edgesOfGrade.add(adjacencyList.getKey());
            }
        }
        return edgesOfGrade;
    }
    /*
    public static Graph removeVerticesFromGraph(Graph graph, Set<Vertex> vertices) {
        Set<Vertex> verticesLeftOver = SetHandler.subtractSets(graph.getVertices(), vertices);
        Graph newGraph = new Graph(verticesLeftOver.size());
        for(Vertex vertex : verticesLeftOver) {

        }
    }*/

    public static Graph makeGraphWithSetOfEdges(int numberOfVertices, Set<Edge> edges) {
        Graph graph = new Graph(numberOfVertices);
        for(Edge edge : edges) {
            graph.addEdge(edge);
        }
        graph.buildAdjacencyLists();
        return graph;
    }
}
