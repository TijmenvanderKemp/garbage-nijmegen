package com.tijmen.entities;

import java.util.Set;
import java.util.stream.Collectors;

public class GraphHandler {

    /**
     * Removes all vertices and edges that contain those vertices from a graph
     * @param graph the original graph to remove vertices from
     * @param vertices the set of all vertices to remove
     * @return the new graph
     */
    public static Graph removeVerticesFromGraph(Graph graph, Set<Vertex> vertices) {
        // subtract the set of edges to be removed
        Set<Vertex> verticesLeftOver = SetHandler.subtractSets(graph.getVertices(), vertices);

        // each edge that contains one of the removed vertices is removed as well
        Set<Edge> edgesLeftOver = graph.getEdges();
        for(Vertex vertex : vertices) {
            Set<Edge> edgesFromOrToVertex = edgesFromOrToVertex(graph, vertex);
            edgesLeftOver = SetHandler.subtractSets(edgesLeftOver, edgesFromOrToVertex);
        }

        return new Graph(verticesLeftOver, edgesLeftOver);
    }

    /**
     * Finds the set of edges that are to or from vertex
     * @param graph
     * @param vertex The vertex to check
     * @return the set of edges that are to or from vertex
     */
    public static Set<Edge> edgesFromOrToVertex(Graph graph, Vertex vertex) {
        Set<Vertex> neighbours = graph.getAdjacencyLists().get(vertex);
        return neighbours.stream()
                .map(neighbour -> new Edge(vertex, neighbour))
                .collect(Collectors.toSet());
    }

}
