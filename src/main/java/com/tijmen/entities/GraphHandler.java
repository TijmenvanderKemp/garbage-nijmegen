package com.tijmen.entities;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphHandler {

    /**
     *
     * @param graph the graph
     * @param grade the requested grade
     * @return all vertices that have grade of requested grade
     */
    public static Set<Vertex> verticesOfGrade(Graph graph, int grade) {
        Set<Vertex> edgesOfGrade = new HashSet<>();
        for(Map.Entry<Vertex, Set<Vertex>> adjacencyList : graph.getAdjacencyLists().entrySet()) {
            if(adjacencyList.getValue().size() == grade) {
                edgesOfGrade.add(adjacencyList.getKey());
            }
        }
        return edgesOfGrade;
    }

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
        // TODO: Something simpler with collectors?
        Set<Edge> edgesLeftOver = graph.getEdges();
        for(Vertex vertex : vertices) {
            Set<Edge> edgesFromOrToVertex = edgesFromOrToVertex(edgesLeftOver, vertex);
            edgesLeftOver = SetHandler.subtractSets(edgesLeftOver, edgesFromOrToVertex);
        }

        return new Graph(verticesLeftOver, edgesLeftOver);
    }

    public static Graph removeVerticesFromGraph(Graph graph, Vertex vertex) {
        Set<Vertex> vertices = new HashSet<>();
        vertices.add(vertex);
        return removeVerticesFromGraph(graph, vertices);
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
