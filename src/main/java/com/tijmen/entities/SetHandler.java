package com.tijmen.entities;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SetHandler {


    static <T> Set<T> subtractSets(Set<T> set1, Set<T> set2) {
        return set1.stream().filter(element -> !set2.contains(element)).collect(Collectors.toSet());
    }
}
