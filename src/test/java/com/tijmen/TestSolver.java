package com.tijmen;

import com.tijmen.entities.Problem;

import java.io.InputStream;

public final class TestSolver {
  private TestSolver() {
  }

  public static boolean solve(String file) {
    ProblemParser parser = new ProblemParser();
    InputStream testFile = TestFile.get(file);
    Problem problem = parser.parse(testFile);
    Algorithm algorithm = new Algorithm(problem);
    return algorithm.solve();
  }

  public static Algorithm algorithm(String file) {
    ProblemParser parser = new ProblemParser();
    InputStream testFile = TestFile.get(file);
    Problem problem = parser.parse(testFile);
    return new Algorithm(problem);
  }
}
