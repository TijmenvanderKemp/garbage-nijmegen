package com.tijmen.entities;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
            Set<Edge> edgesFromOrToVertex = edgesFromOrToVertex(edgesLeftOver, vertex);
            edgesLeftOver = SetHandler.subtractSets(edgesLeftOver, edgesFromOrToVertex);
        }

        return new Graph(verticesLeftOver, edgesLeftOver);
    }

    /**
     * Finds the set of edges that are to or from vertex
     * @param edges The list of edges in the graph
     * @param vertex The vertex to check
     * @return the set of edges that are to or from vertex
     */
    public static Set<Edge> edgesFromOrToVertex(Set<Edge> edges, Vertex vertex) {
        Set<Edge> edgesFromOrToVertex = new HashSet<>();
        for(Edge edge : edges) {
            if(edge.connectedTo(vertex)) {
                edgesFromOrToVertex.add(edge);
            }
        }
        return edgesFromOrToVertex;
    }

}
