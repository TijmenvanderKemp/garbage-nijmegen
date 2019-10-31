package com.tijmen.entities;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SetHandler {

    /**
     * Subtracts two sets
     * @param set1 the subtract to subtract from
     * @param set2 the set to be removed
     * @return A portion of set1 that does not contain any elements from set2
     */
    public static <T> Set<T> subtractSets(Set<T> set1, Set<T> set2) {
        return set1.stream().filter(element -> !set2.contains(element)).collect(Collectors.toSet());
    }
}
