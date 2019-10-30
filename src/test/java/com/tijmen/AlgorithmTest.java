package com.tijmen;

import com.tijmen.entities.Pair;
import com.tijmen.entities.Problem;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AlgorithmTest {

    @Test
    public void testAlleOfficieleFiles() {
        File inFolder = new File(TestFile.getAsUrl("OfficialTestFiles/in/").getPath());
        File[] inFiles = Objects.requireNonNull(inFolder.listFiles());

        File outFolder = new File(TestFile.getAsUrl("OfficialTestFiles/out/").getPath());
        File[] outFiles = Objects.requireNonNull(outFolder.listFiles());

        List<Pair<File, File>> insAndOuts = zip(inFiles, outFiles);

        for (Pair<File, File> inAndOut : insAndOuts) {
            test(inAndOut);
        }
    }

    private void test(Pair<File, File> inAndOut) {
        try {
            System.out.println("Testing " + inAndOut.getLeft().getName());
            Problem in = getProblem(inAndOut);
            String solution = getSolution(inAndOut);
            testSolve(in, solution);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ComparisonFailure comparisonFailure) {
            System.out.println(inAndOut.getLeft().getName());
            throw comparisonFailure;
        }
    }

    private void testSolve(Problem in, String solution) {
        boolean solve = new Algorithm(in, new HashMap<>()).solve();
        if (solve) {
            assertThat(solution).isEqualTo("possible");
        } else {
            assertThat(solution).isEqualTo("impossible");
        }
    }

    private Problem getProblem(Pair<File, File> inAndOut) throws FileNotFoundException {
        return new ProblemParser().parse(new FileInputStream(inAndOut.getLeft()));
    }

    private String getSolution(Pair<File, File> inAndOut) throws FileNotFoundException {
        return new Scanner(new FileInputStream(inAndOut.getRight())).nextLine();
    }

    public <A, B> List<Pair<A, B>> zip(A[] lefts, B[] rights) {
        if (lefts.length != rights.length) {
            throw new IllegalStateException("lijsten zijn niet even lang");
        }
        List<Pair<A, B>> pairs = new ArrayList<>();
        for (int i = 0; i < lefts.length; i++) {
            pairs.add(new Pair<>(lefts[i], rights[i]));
        }
        return pairs;
    }

}