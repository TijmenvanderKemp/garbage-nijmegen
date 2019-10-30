package com.tijmen;

import com.tijmen.entities.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.DoubleStream;

public class Algorithm {
  private int numberOfBins;
  private Graph graph;

  public Algorithm(Problem problem) {
    numberOfBins = problem.getNumberOfBins();
    graph = problem.getGraph();
  }

  public boolean solve() {

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
    if (numberOfBins == 0) {
      return true;
    }

    if(tryToFillTheRest(graph, numberOfBins)) {
      return true;
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
    for(Optional<Vertex> gradeOneVertex = GraphHandler.verticesOfGrade(graph, 1).stream().findAny();
        gradeOneVertex.isPresent() && numberOfBins>0;
        // each time update which vertex to remove
        gradeOneVertex = GraphHandler.verticesOfGrade(graph, 1).stream().findAny()) {
      numberOfBins--;
      Set<Vertex> removedVertices = new HashSet<>();
      // We will remove both the vertex of grade one and its neighbour.
      removedVertices.add(gradeOneVertex.get());
      // For the neighbour we don't check for isPresent because we know the vertex is of grade 1.
      removedVertices.add(GraphHandler
              .VerticesDirectlyConnectedToVertex(graph, gradeOneVertex.get())
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
    return numberOfBins <= V / (E*2.0/V + 1);
  }

  public boolean tryToFillTheRest(Graph graph, int numberOfBins) {
    // if(numberOfBins > 15) System.out.println(numberOfBins); // See depth
    if(numberOfBins == 0) {
      return true;
    }

    Set<Vertex> vertices = graph.getVertices();
    if(vertices.isEmpty()) {
      return false;
    }
    Vertex vertex = GraphHandler.verticesSortedByGrade(graph).get(0);

    Set<Vertex> neighbours = GraphHandler.VerticesDirectlyConnectedToVertex(graph, vertex);
    Set<Vertex> removedVertices = new HashSet<>(neighbours);
    removedVertices.add(vertex);

    if(tryToFillTheRest(GraphHandler.removeVerticesFromGraph(graph, removedVertices), numberOfBins - 1)) {
      return true;
    }

    //TODO: Sort neighbours by grade.
    for(Vertex neighbour : neighbours) {
      Set<Vertex> removedVerticesNeighbour = GraphHandler.VerticesDirectlyConnectedToVertex(graph, neighbour);
      removedVerticesNeighbour.add(neighbour);
      if(tryToFillTheRest(GraphHandler.removeVerticesFromGraph(graph, removedVerticesNeighbour), numberOfBins - 1)) {
        return true;
      }
    }

    return false;

  }
}