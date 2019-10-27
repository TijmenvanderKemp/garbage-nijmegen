package com.tijmen;

import com.tijmen.entities.Graph;
import com.tijmen.entities.GraphHandler;
import com.tijmen.entities.Problem;
import com.tijmen.entities.Vertex;

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
    //fillAllGradeZeroVertices();

    return false;
  }

  /**
   * Creates a new graph with all vertices removed that have zero neighbours.
   * Reduces the number of bins by the amount of vertices removed.
   */
  /*
  private void fillAllGradeZeroVertices() {
    Set<Vertex> gradeZeroVertices = GraphHandler.VerticesOfGrade(graph, 0);
    int numberRemoved = gradeZeroVertices.size();
    graph = GraphHandler.removeVerticesFromGraph(graph, gradeZeroVertices);
  }*/

  /**
   * If the number of bins k is less than or equal to the number of vertices |V|, divided by 5, rounded up,
   * then it's automatically possible to place all bins.
   */
  private boolean testVertexLowerBound() {
    return numberOfBins <= Math.ceil(graph.numberOfVertices() / 5.0);
  }

  /**
   * If the number of bins k is less than or equal to the number of vertices |V|, divided by the average grade
   * of all vertices d plus one, then it's automatically possible to place all bins.
   * (k <= |V| / (d+1))
   */
  private boolean testAverageGradeLowerBound() {
    OptionalDouble optionalAverage = GraphHandler.averageGrade(graph);
    double average;
    if(optionalAverage.isPresent()) {
      average = optionalAverage.getAsDouble();
    } else {
      // if there is no average, then that must mean there are no edges at all so the average is 0.
      average = 0;
    }
    return numberOfBins <= graph.numberOfVertices() / (average + 1);
  }
}