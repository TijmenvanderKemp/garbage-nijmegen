package com.tijmen.entities;

public class Problem {
    private Graph graph;
    private int numberOfBins;

    public Problem(Graph graph, int numberOfBins) {
        this.graph = graph;
        this.numberOfBins = numberOfBins;
    }

    public Graph getGraph() {
        return graph;
    }

    public int getNumberOfBins() {
        return numberOfBins;
    }
}
