package com.tijmen;

import com.tijmen.entities.Graph;
import com.tijmen.entities.Problem;

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
    return false;
  }

  /**
   * If the number of bins k is less than or equal to the number of vertices v, divided by 5, rounded up,
   * then it's automatically possible to place all bins.
   */
  private boolean testVertexLowerBound() {
    return numberOfBins <= Math.ceil(graph.numberOfVertices() / 5.0);
  }
}
