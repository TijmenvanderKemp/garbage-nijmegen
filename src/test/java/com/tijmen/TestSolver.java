package com.tijmen;

import com.tijmen.entities.Graph;
import com.tijmen.entities.Problem;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;

public final class TestSolver {
  private TestSolver() {
  }

  public static boolean solve(String file) {
    ProblemParser parser = new ProblemParser();
    InputStream testFile = TestFile.getAsStream(file);
    Problem problem = parser.parse(testFile);
    Algorithm algorithm = new Algorithm(problem, new HashMap<>());
    return algorithm.solve();
  }

  public static Algorithm algorithm(String file) {
    ProblemParser parser = new ProblemParser();
    InputStream testFile = TestFile.getAsStream(file);
    Problem problem = parser.parse(testFile);
    return new Algorithm(problem, new HashMap<>());
  }
}
