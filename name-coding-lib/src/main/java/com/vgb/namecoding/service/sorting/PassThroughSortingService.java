package com.vgb.namecoding.service.sorting;

import java.util.Collection;

/**
 * This implementation of the sorting-service is a NO-OP. It is basically
 * a placeholder in the processing pipeline that allows us to substitute a valid
 * custom sorter if desired. It is intended to be used when the input is already
 * pre-sorted
 * NOTE: Sorting is now possible when data is read via the @{@link com.vgb.namecoding.service.reader.ReaderService},
 * so no need to
 * use extra CPU cycles or memory to re-sort it
 */
public class PassThroughSortingService implements SortingService<String> {
    @Override
    public Collection<String> sort(Collection<String> unsortedCollection) {
        return unsortedCollection;
    }
}
