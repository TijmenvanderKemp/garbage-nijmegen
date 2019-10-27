package com.tijmen.entities;

import java.util.OptionalDouble;

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
}
