package com.tijmen;

import com.tijmen.entities.Edge;
import com.tijmen.entities.Graph;
import com.tijmen.entities.GraphHandler;
import com.tijmen.entities.Vertex;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphHandlerTests {

    @Test
    public void removeVerticesFromGraphTest() {
        Graph graph1 = new Graph(3);
        graph1.addEdge(1,2);
        graph1.addEdge(2,3);
        graph1.buildAdjacencyLists();

        Graph graph2 = new Graph(2);
        graph2.addEdge(1,2);

        Set<Vertex> vertex3 = new HashSet<>();
        vertex3.add(new Vertex(3));

        assertThat(GraphHandler.removeVerticesFromGraph(graph1, vertex3).equals(graph2));
    }

    @Test
    public void edgesFromOrToVertexTest() {
        Graph graph = new Graph(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.buildAdjacencyLists();
        assertThat(GraphHandler.edgesFromOrToVertex(graph, new Vertex(1))).containsExactly(new Edge(1, 2));
        assertThat(GraphHandler.edgesFromOrToVertex(graph, new Vertex(2))).containsExactlyInAnyOrder(new Edge(1, 2), new Edge(2, 3));
    }

    @Test
    public void ActuallyEqual() {
        Set<Vertex> vertices = Set.of(new Vertex(1), new Vertex(2), new Vertex(3));
        Edge e1 = new Edge(1, 2);
        Edge e2 = new Edge(2, 3);
        Set<Edge> edges = Set.of(e1, e2);
        Graph graph1 = new Graph(3);
        graph1.addEdge(e1);
        graph1.addEdge(e2);
        Graph graph2 = new Graph(vertices, edges);
        assertThat(graph1.hashCode() == graph2.hashCode());
        assertThat(graph1.equals(graph2));
        assertThat(graph2.equals(graph1));
    }

    @Test
    public void NotEqual() {
        Set<Vertex> vertices = Set.of(new Vertex(1), new Vertex(2), new Vertex(3));
        Edge e1 = new Edge(1, 2);
        Edge e2 = new Edge(2, 3);
        Graph graph1 = new Graph(vertices, Set.of(e1));
        Graph graph2 = new Graph(vertices, Set.of(e1, e2));
        assertThat(graph1.hashCode() != graph2.hashCode());
        assertThat(!graph1.equals(graph2));
        assertThat(!graph2.equals(graph1));
    }
}
