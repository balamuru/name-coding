package com.vgb.namecoding.service.sorting;

import java.util.Collection;

public interface SortingService<T>{

    Collection<T> sort(Collection<T> unsortedCollection);


}
