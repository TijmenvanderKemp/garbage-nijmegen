package com.tijmen.entities;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class EdgeTest {
    @Test
    public void testEquals() {
        Edge edge12 = new Edge(new Vertex(1), new Vertex(2));
        Edge edge21 = new Edge(new Vertex(2), new Vertex(1));
        Edge edge13 = new Edge(new Vertex(1), new Vertex(3));

        assertThat(edge13).isNotEqualTo(edge12);
        assertThat(edge12).isEqualTo(edge21);
    }

    @Test
    public void testHash() {
        Edge edge12 = new Edge(new Vertex(1), new Vertex(2));
        Edge edge21 = new Edge(new Vertex(2), new Vertex(1));
        Edge edge13 = new Edge(new Vertex(1), new Vertex(3));

        assertThat(edge13.hashCode()).isNotEqualTo(edge12.hashCode());
        assertThat(edge12.hashCode()).isEqualTo(edge21.hashCode());
    }

}