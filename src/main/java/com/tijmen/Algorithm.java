package com.tijmen;

import com.tijmen.entities.Graph;
import com.tijmen.entities.GraphHandler;
import com.tijmen.entities.Problem;
import com.tijmen.entities.Vertex;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

class Algorithm {
    private int numberOfBins;
    private Graph graph;
    private HashMap<Graph, Integer> impossibleProblems;

    Algorithm(Problem problem, HashMap<Graph, Integer> impossibleProblems) {
        numberOfBins = problem.getNumberOfBins();
        graph = problem.getGraph();
        this.impossibleProblems = impossibleProblems;
    }

    boolean solve() {

        if (simplyPossible()) {
            return true;
        }
        if (simplyImpossible()) {
            impossibleProblems.put(graph, numberOfBins);
            return false;
        }

        // Every vertex with 0 edges can support a bin.
        fillAllGradeZeroVertices();
        if (simplyPossible()) {
            return true;
        }
        if (simplyImpossible()) {
            impossibleProblems.put(graph, numberOfBins);
            return false;
        }

        // Each time we encounter a vertex of grade 1 we see it is never disadvantageous to fill it.
        fillAsManyAsPossibleGradeOneVertices();
        if (simplyPossible()) {
            return true;
        }
        if (simplyImpossible()) {
            impossibleProblems.put(graph, numberOfBins);
            return false;
        }

        // Grade 0 vertices can be removed again
        fillAllGradeZeroVertices();
        if (simplyPossible()) {
            return true;
        }
        if (simplyImpossible()) {
            impossibleProblems.put(graph, numberOfBins);
            return false;
        }

        return tryToPlaceABinOnGradeTwoOrMoreVertex();
    }

    private boolean tryToPlaceABinOnGradeTwoOrMoreVertex() {
        // If there is a solution, there must be a solution with one of these filled.
        return getVertexWithLowestGradeAndItsNeighbours().stream()
                .sorted(Comparator.comparing(graph::getGrade))
                .map(graph::getVertexAndNeighbours)
                .map(vertexAndNeighbours -> GraphHandler.removeVerticesFromGraph(graph, vertexAndNeighbours))
                .anyMatch(this::tryToSolveSubproblem);
    }

    /**
     * Try to solve the subproblem. We're making a new Algorithm to avoid modifying the state of the current solve.
     * We do pass along the impossibleProblems so we can keep track of all solutions that didn't work out.
     */
    private boolean tryToSolveSubproblem(Graph subGraph) {
        Algorithm algorithm = new Algorithm(new Problem(subGraph, numberOfBins - 1), impossibleProblems);
        if (algorithm.solve()) {
            return true;
        } else {
            impossibleProblems.put(subGraph, numberOfBins - 1);
            return false;
        }
    }

    private Set<Vertex> getVertexWithLowestGradeAndItsNeighbours() {
        Vertex vertex = graph.getVertexWithMinimalGrade();
        return graph.getVertexAndNeighbours(vertex);
    }

    // Applies a few simple tests to see if the problem is possible
    private boolean simplyPossible() {
        return numberOfBins <= 0 || testVertexLowerBound() || testAverageGradeLowerBound();
    }

    // Applies a few simple tests to see if the problem is impossible
    private boolean simplyImpossible() {
        return graph.isEmpty() || hasBeenCheckedBefore();
    }

    // If we encountered this sub graph before, its not possible.
    private boolean hasBeenCheckedBefore() {
        if (impossibleProblems.containsKey(graph)) {
            return numberOfBins >= impossibleProblems.get(graph);
        }
        return false;
    }

    /**
     * Creates a new graph with all vertices removed that have zero neighbours.
     * Reduces the number of bins by the amount of vertices removed.
     */
    int fillAllGradeZeroVertices() {
        Set<Vertex> gradeZeroVertices = graph.getVerticesWithGrade(0);
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
        // each time update which vertex to remove
        Optional<Vertex> gradeOneVertex = graph.findVertexWithGrade(1);
        while (gradeOneVertex.isPresent() && numberOfBins > 0) {
            removeGradeOneVertexAndItsNeighbour(gradeOneVertex.get());
            gradeOneVertex = graph.findVertexWithGrade(1);
        }
    }

    private void removeGradeOneVertexAndItsNeighbour(Vertex gradeOne) {
        numberOfBins--;
        Set<Vertex> verticesToRemove = graph.getVertexAndNeighbours(gradeOne);
        graph = GraphHandler.removeVerticesFromGraph(graph, verticesToRemove);
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
}