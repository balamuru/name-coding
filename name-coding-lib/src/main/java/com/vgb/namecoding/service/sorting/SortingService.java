package com.vgb.namecoding.service.sorting;

import java.util.Collection;

/**
 * Sorts the input collection
 * @param <T> data type
 */
public interface SortingService<T>{

    /**
     * Creates a new sorted collection out of the unsorted data
     * @param unsortedCollection unsorted data collection
     * @return sorted data collection
     */
    Collection<T> sort(Collection<T> unsortedCollection);


}
