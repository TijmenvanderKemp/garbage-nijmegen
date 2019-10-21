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

  private boolean testVertexLowerBound() {
    return numberOfBins <= Math.ceil(graph.numberOfVertices() / 5.0);
  }
}
