package com.tijmen;

import com.tijmen.entities.Problem;
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
}