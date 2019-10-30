package com.tijmen;

import com.tijmen.entities.Graph;
import com.tijmen.entities.GraphHandler;
import com.tijmen.entities.Problem;
import com.tijmen.entities.Vertex;

import java.util.*;

class Algorithm {
    private int numberOfBins;
    private Graph graph;
    private HashMap<Graph, Integer> subGraphs;

    Algorithm(Problem problem, HashMap<Graph, Integer> subGraphs) {
        numberOfBins = problem.getNumberOfBins();
        graph = problem.getGraph();
        this.subGraphs = subGraphs;
    }

    boolean solve() {

        if (testVertexLowerBound()) {
            return true;
        }
        if (testAverageGradeLowerBound()) {
            return true;
        }

        // Every vertex with 0 edges can support a bin.
        fillAllGradeZeroVertices();
        if (numberOfBins <= 0) {
            return true;
        }
        // Each time we encounter a vertex of grade 1 we see it is never disadvantageous to fill it.
        fillAsManyAsPossibleGradeOneVertices();
        if (numberOfBins <= 0) {
            return true;
        }

        // If there is no more space left, its not possible
        Set<Vertex> vertices = graph.getVertices();
        if (vertices.isEmpty()) {
            return false;
        }

        // If we encountered this sub graph before, its not possible.
        if (subGraphs.containsKey(graph)) {
            if(numberOfBins >= subGraphs.get(graph)) {
                return false;
            }
        }

        Vertex vertex = graph.getVertexWithMinimalGrade();

        List<Vertex> neighbours = new ArrayList<>(graph.getAdjacencyLists().get(vertex));
        neighbours.add(vertex);
        neighbours.sort(Comparator.comparing(graph::getGrade));

        for (Vertex neighbour : neighbours) {
            Set<Vertex> removedVerticesNeighbour = graph.getAdjacencyLists().get(neighbour);
            removedVerticesNeighbour.add(neighbour);
            Graph subGraph = GraphHandler.removeVerticesFromGraph(graph, removedVerticesNeighbour);
            Algorithm algorithm = new Algorithm(new Problem(subGraph, numberOfBins - 1), subGraphs);
            if (algorithm.solve()) {
                return true;
            } else {
                subGraphs.put(subGraph, numberOfBins);
            }

        }

        return false;

    }

    /**
     * Creates a new graph with all vertices removed that have zero neighbours.
     * Reduces the number of bins by the amount of vertices removed.
     */
    int fillAllGradeZeroVertices() {
        Set<Vertex> gradeZeroVertices = GraphHandler.verticesOfGrade(graph, 0);
        int numberRemoved = gradeZeroVertices.size();
        graph = GraphHandler.removeVerticesFromGraph(graph, gradeZeroVertices);
        numberOfBins -= numberRemoved;
        return numberRemoved;
    }

    /**
     * Creates graphs with a single grade one vertex and its neighbour removed.
     * Reduces number of bins by 1 each time.
     * Keeps doing this until no more vertices with grade 1 exist.
     */
    private void fillAsManyAsPossibleGradeOneVertices() {
        for (Optional<Vertex> gradeOneVertex = GraphHandler.verticesOfGrade(graph, 1).stream().findAny();
             gradeOneVertex.isPresent() && numberOfBins > 0;
            // each time update which vertex to remove
             gradeOneVertex = GraphHandler.verticesOfGrade(graph, 1).stream().findAny()) {
            numberOfBins--;
            Set<Vertex> removedVertices = new HashSet<>();
            // We will remove both the vertex of grade one and its neighbour.
            removedVertices.add(gradeOneVertex.get());
            // For the neighbour we don't check for isPresent because we know the vertex is of grade 1.
            removedVertices.add(graph.getAdjacencyLists().get(gradeOneVertex.get())
                    .stream()
                    .findAny()
                    .get());

            // actually remove the vertices
            graph = GraphHandler.removeVerticesFromGraph(graph, removedVertices);
        }
    }

    /**
     * If the number of bins k is less than or equal to the number of vertices |V|, divided by 5, rounded up,
     * then it's automatically possible to place all bins.
     */
    boolean testVertexLowerBound() {
        return numberOfBins <= Math.ceil(graph.numberOfVertices() / 5.0);
    }

    /**
     * If the number of bins k is less than or equal to the number of vertices |V|, divided by the average grade
     * of all vertices d plus one, then it's automatically possible to place all bins.
     * (k <= |V| / (d+1))
     * We see that for a bidirectional graph the average grade d is d = |E|/|V|
     * We save our edges only once in memory so 2E
     */
    boolean testAverageGradeLowerBound() {
        int E = graph.numberOfEdges();
        int V = graph.numberOfVertices();
        return numberOfBins <= V / (E * 2.0 / V + 1);
    }

    public HashMap<Graph, Integer> getSubGraphs() {
        return subGraphs;
    }
}