package com.vgb.namecoding.service.sorting;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;


public class TreeMapSortingService implements SortingService<String> {
    @Override
    public Collection <String> sort(Collection <String> unsortedCollection) {
        final SortedSet<String> sortedSet = new TreeSet<>();
        unsortedCollection.forEach(s1 -> sortedSet.add(s1));
        return Collections.unmodifiableSet(sortedSet);

    }
}
