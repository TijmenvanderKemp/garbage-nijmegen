package com.tijmen;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VertexLowerBoundTest {

  /**
   * If the number of bins k is less than or equal to the number of vertices v, divided by 5, rounded up,
   * then it's automatically possible to place all bins.
   */
  @Test
  public void twoBinsIsMoreThanFiveDividedByFiveCeilinged() {
    assertThat(TestSolver.algorithm("fiveVerticesTwoBins.txt").testVertexLowerBound()).isFalse();
  }

  @Test
  public void twoBinsIsLessOrEqualThanSixDividedByFiveCeilinged() {
    assertThat(TestSolver.algorithm("sixVerticesTwoBins.txt").testVertexLowerBound()).isTrue();
  }
}