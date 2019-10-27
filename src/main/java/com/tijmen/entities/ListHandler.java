package com.tijmen.entities;

import java.util.List;
import java.util.stream.Collectors;

public class ListHandler {


    static <T> List<T> subtractLists(List<T> list1, List<T> list2) {
        return list1.stream().filter(element -> !list2.contains(element)).collect(Collectors.toList());
    }
}
