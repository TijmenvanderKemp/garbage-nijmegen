package com.tijmen;

import org.junit.Test;

import java.io.File;
import java.util.Objects;

public class AlgorithmTest {

    @Test
    public void testAlleOfficieleFiles() {
        File inFolder = new File("OfficialTestFiles/in/");
        File[] inFiles = Objects.requireNonNull(inFolder.listFiles());

        File outFolder = new File("OfficialTestFiles/out/");
        File[] outFiles = Objects.requireNonNull(outFolder.listFiles());
        System.out.println(inFiles);
    }

}