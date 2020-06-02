package com.vgb.namecoding.service;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class TreeMapNameSortingService implements NameSortingService<String> {
    @Override
    public Collection <String> sort(Collection <String> unsortedCollection) {
        final SortedSet<String> sortedSet = new TreeSet<>();
        unsortedCollection.forEach(s1 -> sortedSet.add(s1));
        return Collections.unmodifiableSet(sortedSet);

    }
}
