package com.tijmen;

import com.tijmen.entities.Edge;
import com.tijmen.entities.Problem;
import com.tijmen.entities.Vertex;
import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ProblemParserTest {

  @Test
  public void testSample1() {
    ProblemParser parser = new ProblemParser();
    InputStream testFile = TestFile.getAsStream("sample1.txt");
    Problem problem = parser.parse(testFile);

    assertThat(problem.getGraph().getVertices()).containsExactlyInAnyOrder(
        new Vertex(1),
        new Vertex(2),
        new Vertex(3),
        new Vertex(4),
        new Vertex(5),
        new Vertex(6),
        new Vertex(7));
    assertThat(problem.getGraph().getEdges()).containsExactlyInAnyOrder(
        edge(1, 2),
        edge(1, 4),
        edge(2, 3),
        edge(2, 5),
        edge(4, 5),
        edge(5, 6),
        edge(5, 7),
        edge(6, 7)
    );
    assertThat(problem.getNumberOfBins()).isEqualTo(4);
  }

  @Test
  public void testSample2() {
    ProblemParser parser = new ProblemParser();
    InputStream testFile = TestFile.getAsStream("sample2.txt");
    Problem problem = parser.parse(testFile);

    assertThat(problem.getGraph().getVertices()).containsExactlyInAnyOrder(
        new Vertex(1),
        new Vertex(2),
        new Vertex(3),
        new Vertex(4),
        new Vertex(5),
        new Vertex(6),
        new Vertex(7),
        new Vertex(8));
    assertThat(problem.getGraph().getEdges()).containsExactlyInAnyOrder(
        edge(1, 2),
        edge(1, 4),
        edge(2, 3),
        edge(2, 5),
        edge(4, 5),
        edge(5, 6),
        edge(5, 7),
        edge(6, 8),
        edge(7, 8)
    );
    assertThat(problem.getNumberOfBins()).isEqualTo(3);
  }

  @Test
  public void testEmptyGraphOneBin() {
    ProblemParser parser = new ProblemParser();
    InputStream testFile = TestFile.getAsStream("emptyGraphOneBin.txt");
    Problem problem = parser.parse(testFile);

    assertThat(problem.getGraph().getVertices()).isEmpty();
    assertThat(problem.getGraph().getEdges()).isEmpty();
    assertThat(problem.getNumberOfBins()).isEqualTo(1);
  }

  @Test
  public void testEmptyGraphZeroBins() {
    ProblemParser parser = new ProblemParser();
    InputStream testFile = TestFile.getAsStream("emptyGraphZeroBins.txt");
    Problem problem = parser.parse(testFile);

    assertThat(problem.getGraph().getVertices()).isEmpty();
    assertThat(problem.getGraph().getEdges()).isEmpty();
    assertThat(problem.getNumberOfBins()).isEqualTo(0);
  }

  private Edge edge(int v1, int v2) {
    return new Edge(new Vertex(v1), new Vertex(v2));
  }
}