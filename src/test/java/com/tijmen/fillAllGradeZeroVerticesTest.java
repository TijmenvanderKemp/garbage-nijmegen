package com.tijmen;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class fillAllGradeZeroVerticesTest {

    @Test
    public void AllZeroesAreRemoved() {
        Algorithm algorithm = TestSolver.algorithm("TenGradeZeroVerticesTenBins.txt");
        assertThat(algorithm.fillAllGradeZeroVertices()).isEqualTo(10);
    }
}
