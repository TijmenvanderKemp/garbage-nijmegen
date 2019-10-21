package com.tijmen;

import com.tijmen.entities.Edge;
import com.tijmen.entities.Problem;
import com.tijmen.entities.Vertex;
import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class VertexLowerBoundTest {

  @Test
  public void twoBinsIsMoreThanFiveDividedByFiveCeilinged() {
    ProblemParser parser = new ProblemParser();
    InputStream testFile = TestFile.get("fiveVerticesTwoBins.txt");
    Problem problem = parser.parse(testFile);
    Algorithm algorithm = new Algorithm(problem);

    assertThat(algorithm.solve()).isFalse();
  }

  @Test
  public void twoBinsIsLessOrEqualThanSixDividedByFiveCeilinged() {
    ProblemParser parser = new ProblemParser();
    InputStream testFile = TestFile.get("sixVerticesTwoBins.txt");
    Problem problem = parser.parse(testFile);
    Algorithm algorithm = new Algorithm(problem);

    assertThat(algorithm.solve()).isTrue();
  }

  private Edge edge(int v1, int v2) {
    return new Edge(new Vertex(v1), new Vertex(v2));
  }
}