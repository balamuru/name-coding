package com.vgb.namecoding.service.sorting;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

/**
 * A @{@link java.util.TreeSet} backed implementation of @{@link SortingService}. This is not needed if the input is
 * pre-sorted. In such a situation, use @{@link PassThroughSortingService}
 */
public class TreeSetSortingService implements SortingService<String> {
    @Override
    public Collection <String> sort(Collection <String> unsortedCollection) {
        //create a new tree set
        //insert each item into the tree set (sorted) O(log(n))
        //return an unmodifiable view of the sorted set
        return Collections.unmodifiableSet(new TreeSet<>(unsortedCollection));

    }
}
