package com.tijmen;

import com.tijmen.entities.Graph;
import com.tijmen.entities.Problem;

import java.util.HashSet;

public final class App
{
    private App() {
    }

    public static void main( String[] args )
    {
        ProblemParser parser = new ProblemParser();
        Problem problem = parser.parse(System.in);
        Algorithm algorithm = new Algorithm(problem, new HashSet<Graph>());
        System.out.println(algorithm.solve());
    }
}
