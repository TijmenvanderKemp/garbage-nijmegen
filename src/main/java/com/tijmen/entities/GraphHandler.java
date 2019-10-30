package com.tijmen.entities;

import java.util.*;
import java.util.stream.Collectors;

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

    public static List<Vertex> verticesSortedByGrade(Graph graph) {
        List<Vertex> sortedList = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            sortedList.addAll(new LinkedList<>(verticesOfGrade(graph, i)));
        }
        return sortedList;
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

    /**
     * Finds the set of direct neighbours a vertex in a graph
     * @param graph the graph
     * @param vertex The vertex to find neighbours of
     * @return the set of direct neighbours a vertex in a graph
     */
    public static Set<Vertex> VerticesDirectlyConnectedToVertex(Graph graph, Vertex vertex) {
        Set<Vertex> vertices = new HashSet<>();
        Set<Edge> edges = edgesFromOrToVertex(graph.getEdges(), vertex);
        for(Edge edge : edges){
            // We already know these are the edges this vertex is in so we dont need to check isEmpty()
            vertices.add(edge.getOtherVertex(vertex).get());
        }
        return vertices;
    }

}
