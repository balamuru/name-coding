package com.vgb.namecoding.service;

import java.util.Collection;

public interface NameSortingService<T>{

    Collection<T> sort(Collection<T> unsortedCollection);


}
