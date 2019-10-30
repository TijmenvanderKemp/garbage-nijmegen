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
    public void verticesOfGradeTest() {
        Graph graph = new Graph(3);
        graph.addEdge(1,2);
        Set<Vertex> verticesOfGrade0 = new HashSet<>();
        Set<Vertex> verticesOfGrade1 = new HashSet<>();
        verticesOfGrade1.add(new Vertex(1));
        verticesOfGrade1.add(new Vertex(2));
        verticesOfGrade0.add(new Vertex(3));
        assertThat(GraphHandler.verticesOfGrade(graph,0).equals(verticesOfGrade0));
        assertThat(GraphHandler.verticesOfGrade(graph, 1).equals(verticesOfGrade1));
        assertThat(GraphHandler.verticesOfGrade(graph, 2).equals(new HashSet<>()));
    }

    @Test
    public void removeVerticesFromGraphTest() {
        Graph graph1 = new Graph(3);
        graph1.addEdge(1,2);
        graph1.addEdge(2,3);
        graph1.buildAdjacencyLists();
        Graph graph2 = new Graph(2);
        graph1.addEdge(1,2);
        Set<Vertex> vertex3 = new HashSet<>();
        vertex3.add(new Vertex(3));
        assertThat(GraphHandler.removeVerticesFromGraph(graph1, vertex3).equals(graph2));
    }

    @Test
    public void edgesFromOrToVertexTest() {
        Set<Edge> edges = new HashSet<>();
        Set<Edge> edges2 = new HashSet<>();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(2);
        edges.add(new Edge(v1,v2));
        edges.add(new Edge(v2,v3));
        edges2.add(new Edge(v1, v2));
        assertThat(GraphHandler.edgesFromOrToVertex(edges, v1).equals(edges2));
        assertThat(GraphHandler.edgesFromOrToVertex(edges, v2).equals(edges));
    }

    @Test
    public void ActuallyEqual() {
        Set<Edge> edges = new HashSet<>();
        Set<Vertex> vertices = new HashSet<>();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(2);
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        Edge e1 = new Edge(v1,v2);
        Edge e2 = new Edge(v2,v3);
        edges.add(e1);
        edges.add(e2);
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
        Set<Edge> edges = new HashSet<>();
        Set<Vertex> vertices = new HashSet<>();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(2);
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        Edge e1 = new Edge(v1,v2);
        Edge e2 = new Edge(v2,v3);
        edges.add(e1);
        edges.add(e2);
        Graph graph1 = new Graph(3);
        graph1.addEdge(e1);
        Graph graph2 = new Graph(vertices, edges);
        assertThat(graph1.hashCode() != graph2.hashCode());
        assertThat(!graph1.equals(graph2));
        assertThat(!graph2.equals(graph1));
    }
}
