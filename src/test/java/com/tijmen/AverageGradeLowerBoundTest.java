package com.tijmen;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AverageGradeLowerBoundTest {

    /**
     * If the number of bins k is less than or equal to the number of vertices |V|, divided by the average grade
     * of all vertices d plus one, then it's automatically possible to place all bins.
     * (k <= |V| / (d+1))
     */
    @Test
    public void twoBinsIsMoreThanFourDividedByFour() {
        assertThat(TestSolver.solve("averageGradeThreeFourVerticesTwoBins.txt")).isFalse();
    }

    @Test
    public void oneBinIsLessOrEqualThanFourDividedByFour() {
        assertThat(TestSolver.solve("averageGradeThreeFourVerticesOneBin.txt")).isTrue();
    }
}