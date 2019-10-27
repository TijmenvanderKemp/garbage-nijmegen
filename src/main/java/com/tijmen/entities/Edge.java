package com.tijmen.entities;

import java.util.Objects;
import java.util.Optional;

public class Edge {
    private final Vertex v1;
    private final Vertex v2;

    public Edge(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public Optional<Vertex> getOtherVertex(Vertex v) {
        if(v.equals(v1)){
            return Optional.of(v2);
        } else if(v.equals(v2)){
            return Optional.of(v1);
        } else {
            return Optional.empty();
        }
    }

    public boolean connectedTo(Vertex vertex) {
        return vertex.equals(v1) || vertex.equals(v2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (v1.equals(edge.getV1()) &&
                v2.equals(edge.getV2())) ||
                (v1.equals(edge.getV2()) &&
                        v2.equals(edge.getV1())) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                '}';
    }
}
